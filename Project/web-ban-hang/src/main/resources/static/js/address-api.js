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
    const currentValue = selectElement.value?.trim() || "";
    $(selectElement).find('option:not([value=""])').remove();

    data.forEach((item) => {
      const option = new Option(item[nameField], item[nameField]);
      option.dataset.code = item[valueField];
      selectElement.add(option);
    });

    if (currentValue) selectElement.value = currentValue;
  }

  async function findCodeByName(name, type) {
    if (!name) return null;
    const list = await fetchData(`${apiEndpoint}${type}/`);
    const found = list?.find(
      (i) => i.name.trim().toLowerCase() === name.trim().toLowerCase()
    );
    return found ? found.code : null;
  }

  async function setupAddressDropdowns(cityId, districtId, wardId) {
    const citySelect = document.getElementById(cityId);
    const districtSelect = document.getElementById(districtId);
    const wardSelect = document.getElementById(wardId);
    if (!citySelect) return;

    const currentCity = citySelect.value;
    const currentDistrict = districtSelect?.value;
    const currentWard = wardSelect?.value;

    const initSelect2 = (selector, placeholder) => {
      $(selector).select2({
        placeholder,
        allowClear: true,
        width: "100%",
      });
    };

    initSelect2(`#${cityId}`, "Chọn Tỉnh/Thành phố");
    initSelect2(`#${districtId}`, "Chọn Quận/Huyện");
    initSelect2(`#${wardId}`, "Chọn Phường/Xã");

    const cities = await fetchData(`${apiEndpoint}p/`);
    populateSelect(citySelect, cities || [], "code", "name");

    async function loadDistrictsByCityName(cityName) {
      const code = await findCodeByName(cityName, "p");
      if (!code) return;
      const data = await fetchData(`${apiEndpoint}p/${code}?depth=2`);
      populateSelect(districtSelect, data?.districts || [], "code", "name");
    }

    async function loadWardsByDistrictName(districtName) {
      const code = await findCodeByName(districtName, "d");
      if (!code) return;
      const data = await fetchData(`${apiEndpoint}d/${code}?depth=2`);
      populateSelect(wardSelect, data?.wards || [], "code", "name");
    }

    async function handleCityChange() {
      const selectedCity = citySelect.value;
      if (selectedCity) {
        await loadDistrictsByCityName(selectedCity);
      } else {
        $(districtSelect).find('option:not([value=""])').remove();
        $(wardSelect).find('option:not([value=""])').remove();
      }
      $(districtSelect).val(null).trigger("change");
      $(wardSelect).val(null).trigger("change");
    }

    async function handleDistrictChange() {
      const selectedDistrict = districtSelect.value;
      if (selectedDistrict) {
        await loadWardsByDistrictName(selectedDistrict);
      } else {
        $(wardSelect).find('option:not([value=""])').remove();
      }
      $(wardSelect).val(null).trigger("change");
    }

    $(citySelect).on("change select2:select select2:clear", handleCityChange);
    $(districtSelect).on(
      "change select2:select select2:clear",
      handleDistrictChange
    );

    if (currentCity) {
      await loadDistrictsByCityName(currentCity);
      if (currentDistrict) {
        await loadWardsByDistrictName(currentDistrict);
      }

      $(citySelect).val(currentCity).trigger("change.select2");
      $(districtSelect).val(currentDistrict).trigger("change.select2");
      $(wardSelect).val(currentWard).trigger("change.select2");
    }
  }

  setupAddressDropdowns("city", "district", "ward");
  setupAddressDropdowns("addCity", "addDistrict", "addWard");
  setupAddressDropdowns("editCity", "editDistrict", "editWard");
});
