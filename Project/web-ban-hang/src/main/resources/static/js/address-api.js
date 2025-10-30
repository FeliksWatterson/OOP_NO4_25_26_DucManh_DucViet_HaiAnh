document.addEventListener("DOMContentLoaded", () => {
  // API endpoint
  const apiEndpoint = "https://provinces.open-api.vn/api/";

  // Kiểm tra xem jQuery và Select2 đã tải chưa
  if (
    typeof jQuery === "undefined" ||
    typeof jQuery.fn.select2 === "undefined"
  ) {
    console.error("jQuery hoặc Select2 chưa được tải.");
    return;
  }

  // Hàm fetch dữ liệu
  async function fetchData(url) {
    try {
      const response = await fetch(url);
      if (!response.ok) throw new Error("Network response was not ok");
      return await response.json();
    } catch (error) {
      console.error("Failed to fetch data:", error);
      return null;
    }
  }

  // Hàm điền dữ liệu vào <select>
  function populateSelect(selectElement, data, valueField, nameField) {
    if (!selectElement) return;

    // Lấy giá trị hiện tại (quan trọng cho form Edit)
    const currentValue = selectElement.value;

    // Xóa các option cũ (trừ option rỗng đầu tiên)
    $(selectElement).find('option:not([value=""])').remove();

    data.forEach((item) => {
      // Giá trị (value) của option chính là tên (nameField)
      // Backend (Address.java) mong đợi String, không phải Code
      const option = new Option(item[nameField], item[nameField]);
      option.dataset.code = item[valueField]; // Lưu code vào data-attribute để JS dùng
      selectElement.add(option);
    });

    // Đặt lại giá trị hiện tại (nếu có)
    // Điều này đảm bảo th:field="*{city}" của Thymeleaf hoạt động
    if (currentValue) {
      selectElement.value = currentValue;
    }
  }

  // Hàm xử lý logic cho một bộ dropdown
  async function setupAddressDropdowns(cityId, districtId, wardId) {
    const citySelect = document.getElementById(cityId);
    const districtSelect = document.getElementById(districtId);
    const wardSelect = document.getElementById(wardId);

    if (!citySelect) return; // Bỏ qua nếu không tìm thấy bộ dropdown này

    // Lấy giá trị đã lưu từ Thymeleaf (dùng cho form edit)
    const currentCity = citySelect.value;
    const currentDistrict = districtSelect.value;
    const currentWard = wardSelect.value;

    // Khởi tạo Select2 (searchable dropdown)
    const initSelect2 = (selector, placeholder) => {
      $(selector).select2({
        placeholder: placeholder,
        allowClear: true,
        width: "100%",
      });
    };

    initSelect2(`#${cityId}`, "Chọn Tỉnh/Thành phố");
    initSelect2(`#${districtId}`, "Chọn Quận/Huyện");
    initSelect2(`#${wardId}`, "Chọn Phường/Xã");

    // 1. Tải Tỉnh/Thành phố
    const cities = await fetchData(`${apiEndpoint}p/`);
    populateSelect(citySelect, cities || [], "code", "name");

    // 2. Tải Quận/Huyện (chỉ khi có Tỉnh được chọn)
    async function loadDistricts() {
      const selectedCityOption = citySelect.options[citySelect.selectedIndex];
      const cityCode = selectedCityOption
        ? selectedCityOption.dataset.code
        : null;

      // Reset Phường/Xã
      $(wardSelect).find('option:not([value=""])').remove();
      $(wardSelect).val(null).trigger("change"); // Reset Phường

      if (cityCode) {
        const districts = await fetchData(
          `${apiEndpoint}p/${cityCode}?depth=2`
        );
        populateSelect(
          districtSelect,
          districts.districts || [],
          "code",
          "name"
        );
      } else {
        // Reset Quận/Huyện
        $(districtSelect).find('option:not([value=""])').remove();
        $(districtSelect).val(null).trigger("change");
      }
    }

    // 3. Tải Phường/Xã (chỉ khi có Quận được chọn)
    async function loadWards() {
      const selectedDistrictOption =
        districtSelect.options[districtSelect.selectedIndex];
      const districtCode = selectedDistrictOption
        ? selectedDistrictOption.dataset.code
        : null;

      if (districtCode) {
        const wards = await fetchData(
          `${apiEndpoint}d/${districtCode}?depth=2`
        );
        populateSelect(wardSelect, wards.wards || [], "code", "name");
      } else {
        // Reset Phường/Xã
        $(wardSelect).find('option:not([value=""])').remove();
        $(wardSelect).val(null).trigger("change");
      }
    }

    // Gán sự kiện change cho Select2
    // Khi người dùng CHỌN 1 Tỉnh -> tải Quận
    $(citySelect).on("select2:select", loadDistricts);
    // Khi người dùng XÓA 1 Tỉnh -> tải (reset) Quận
    $(citySelect).on("select2:clear", loadDistricts);

    // Khi người dùng CHỌN 1 Quận -> tải Phường
    $(districtSelect).on("select2:select", loadWards);
    // Khi người dùng XÓA 1 Quận -> tải (reset) Phường
    $(districtSelect).on("select2:clear", loadWards);

    // Xử lý tải dữ liệu cho form Edit (khi đã có giá trị ban đầu)
    if (currentCity) {
      // Phải trigger 'change' để Select2 cập nhật UI
      $(citySelect).val(currentCity).trigger("change");

      const selectedCityOption = citySelect.options[citySelect.selectedIndex];
      const cityCode = selectedCityOption
        ? selectedCityOption.dataset.code
        : null;

      if (cityCode) {
        const districts = await fetchData(
          `${apiEndpoint}p/${cityCode}?depth=2`
        );
        populateSelect(
          districtSelect,
          districts.districts || [],
          "code",
          "name"
        );

        if (currentDistrict) {
          $(districtSelect).val(currentDistrict).trigger("change");

          const selectedDistrictOption =
            districtSelect.options[districtSelect.selectedIndex];
          const districtCode = selectedDistrictOption
            ? selectedDistrictOption.dataset.code
            : null;

          if (districtCode) {
            const wards = await fetchData(
              `${apiEndpoint}d/${districtCode}?depth=2`
            );
            populateSelect(wardSelect, wards.wards || [], "code", "name");
            if (currentWard) {
              $(wardSelect).val(currentWard).trigger("change");
            }
          }
        }
      }
    }
  }

  // Chạy hàm cho cả 3 form
  setupAddressDropdowns("city", "district", "ward"); // Trang checkout
  setupAddressDropdowns("addCity", "addDistrict", "addWard"); // Trang address (form thêm)
  setupAddressDropdowns("editCity", "editDistrict", "editWard"); // Trang address (form sửa)
});
