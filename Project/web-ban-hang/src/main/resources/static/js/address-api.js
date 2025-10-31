document.addEventListener("DOMContentLoaded", () => {
  const apiEndpoint = "https://provinces.open-api.vn/api/";

  if (
    typeof jQuery === "undefined" ||
    typeof jQuery.fn.select2 === "undefined"
  ) {
    console.error("jQuery hoặc Select2 chưa được tải.");
    return;
  }

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

  function populateSelect(selectElement, data, valueField, nameField) {
    if (!selectElement) return;

    const currentValue = selectElement.value;

    $(selectElement).find('option:not([value=""])').remove();

    data.forEach((item) => {
      const option = new Option(item[nameField], item[nameField]);
      option.dataset.code = item[valueField];
      selectElement.add(option);
    });

    if (currentValue) {
      selectElement.value = currentValue;
    }
  }

  async function setupAddressDropdowns(cityId, districtId, wardId) {
    const citySelect = document.getElementById(cityId);
    const districtSelect = document.getElementById(districtId);
    const wardSelect = document.getElementById(wardId);

    if (!citySelect) return;
    const currentCity = citySelect.value;
    const currentDistrict = districtSelect.value;
    const currentWard = wardSelect.value;

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

    const cities = await fetchData(`${apiEndpoint}p/`);
    populateSelect(citySelect, cities || [], "code", "name");

    async function loadDistricts() {
      const selectedCityOption = citySelect.options[citySelect.selectedIndex];
      const cityCode = selectedCityOption
        ? selectedCityOption.dataset.code
        : null;

      $(wardSelect).find('option:not([value=""])').remove();
      $(wardSelect).val(null).trigger("change");

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
        $(districtSelect).find('option:not([value=""])').remove();
        $(districtSelect).val(null).trigger("change");
      }
    }

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
        $(wardSelect).find('option:not([value=""])').remove();
        $(wardSelect).val(null).trigger("change");
      }
    }
    $(citySelect).on("select2:select", loadDistricts);
    $(citySelect).on("select2:clear", loadDistricts);
    $(districtSelect).on("select2:select", loadWards);
    $(districtSelect).on("select2:clear", loadWards);
    if (currentCity) {
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

  setupAddressDropdowns("city", "district", "ward");
  setupAddressDropdowns("addCity", "addDistrict", "addWard");
  setupAddressDropdowns("editCity", "editDistrict", "editWard");
});
