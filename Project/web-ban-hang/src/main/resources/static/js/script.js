"use strict";
const modal = document.querySelector("[data-modal]");
const modalCloseBtn = document.querySelector("[data-modal-close]");
const modalCloseOverlay = document.querySelector("[data-modal-overlay]");
const modalCloseFunc = function () {
  if (modal) modal.classList.add("closed");
};
if (modal && modalCloseOverlay && modalCloseBtn) {
  modalCloseOverlay.addEventListener("click", modalCloseFunc);
  modalCloseBtn.addEventListener("click", modalCloseFunc);
}
const mobileMenuOpenBtn = document.querySelectorAll(
  "[data-mobile-menu-open-btn]"
);
const mobileMenu = document.querySelectorAll("[data-mobile-menu]");
const mobileMenuCloseBtn = document.querySelectorAll(
  "[data-mobile-menu-close-btn]"
);
const overlay = document.querySelector("[data-overlay]");
for (let i = 0; i < mobileMenuOpenBtn.length; i++) {
  const mobileMenuCloseFunc = function () {
    if (mobileMenu[i]) mobileMenu[i].classList.remove("active");
    if (overlay) overlay.classList.remove("active");
  };
  mobileMenuOpenBtn[i].addEventListener("click", function () {
    if (mobileMenu[i]) mobileMenu[i].classList.add("active");
    if (overlay) overlay.classList.add("active");
  });
  if (mobileMenuCloseBtn[i])
    mobileMenuCloseBtn[i].addEventListener("click", mobileMenuCloseFunc);
  if (overlay) overlay.addEventListener("click", mobileMenuCloseFunc);
}

document
  .querySelector('a[href="#flash-sale"]')
  .addEventListener("click", function (e) {
    e.preventDefault();
    const flashSale = document.querySelector("#flash-sale");
    const rect = flashSale.getBoundingClientRect();
    const offset =
      rect.top + window.scrollY - window.innerHeight / 2 + rect.height / 2;

    window.scrollTo({
      top: offset,
      behavior: "smooth",
    });
  });

const accordionBtn = document.querySelectorAll("[data-accordion-btn]");
const accordion = document.querySelectorAll("[data-accordion]");
for (let i = 0; i < accordionBtn.length; i++) {
  accordionBtn[i].addEventListener("click", function () {
    const clickedBtnIsActive =
      this.nextElementSibling &&
      this.nextElementSibling.classList.contains("active");
    for (let j = 0; j < accordion.length; j++) {
      if (clickedBtnIsActive) break;
      if (accordion[j] && accordion[j].classList.contains("active")) {
        accordion[j].classList.remove("active");
        if (accordionBtn[j]) {
          accordionBtn[j].classList.remove("active");
        }
      }
    }
    if (this.nextElementSibling) {
      this.nextElementSibling.classList.toggle("active");
      this.classList.toggle("active");
    }
  });
}

const sliders = document.querySelectorAll(
  ".slider-container, .showcase-wrapper.has-scrollbar"
);
const autoSlideInterval = 2000;

sliders.forEach((slider) => {
  let isDown = false;
  let startX;
  let scrollLeft;
  let autoSlideTimer = null;
  let restartTimer = null;

  const sliderWrapper = slider.closest(".slider-with-arrows");
  const prevBtn = sliderWrapper
    ? sliderWrapper.querySelector("[data-slider-prev]")
    : null;
  const nextBtn = sliderWrapper
    ? sliderWrapper.querySelector("[data-slider-next]")
    : null;

  slider.style.scrollSnapType = "none";
  slider.style.scrollBehavior = "auto";

  const isAutoSlideTarget = () => {
    return (
      slider.classList.contains("slider-container") ||
      (slider.classList.contains("showcase-wrapper") &&
        slider.closest(".product-featured"))
    );
  };

  const stopAutoSlide = () => {
    clearInterval(autoSlideTimer);
    autoSlideTimer = null;
    clearTimeout(restartTimer);
  };

  const scheduleRestartAutoSlide = () => {
    if (!isAutoSlideTarget()) return;
    clearTimeout(restartTimer);
    if (!autoSlideTimer) {
      restartTimer = setTimeout(() => {
        if (!isDown) {
          startAutoSlide();
        }
      }, autoSlideInterval);
    }
  };

  const startAutoSlide = () => {
    if (!isAutoSlideTarget() || autoSlideTimer) return;
    stopAutoSlide();

    autoSlideTimer = setInterval(() => {
      const currentScroll = slider.scrollLeft;
      const slideWidth = slider.clientWidth;
      const totalSlides = slider.children.length;
      const maxScrollLeft = slider.scrollWidth - slideWidth;

      let currentNearestSlideIndex = Math.round(currentScroll / slideWidth);
      let nextTargetIndex = (currentNearestSlideIndex + 1) % totalSlides;
      let targetScrollLeft = nextTargetIndex * slideWidth;

      if (targetScrollLeft > maxScrollLeft) {
        if (Math.abs(currentScroll - maxScrollLeft) < 10) {
          targetScrollLeft = 0;
        } else {
          targetScrollLeft = maxScrollLeft;
        }
      }

      if (Math.abs(currentScroll - maxScrollLeft) < 10) {
        targetScrollLeft = 0;
      }

      slider.style.scrollBehavior = "smooth";
      slider.scrollTo({ left: targetScrollLeft });

      setTimeout(() => {
        if (!isDown) {
          slider.style.scrollBehavior = "auto";
        }
      }, 700);
    }, autoSlideInterval);
  };

  slider.addEventListener("mousedown", (e) => {
    isDown = true;
    slider.classList.add("active-drag");
    startX = e.pageX - slider.offsetLeft;
    scrollLeft = slider.scrollLeft;
    stopAutoSlide();
    slider.style.scrollBehavior = "auto";
    e.preventDefault();
  });

  slider.addEventListener("mousemove", (e) => {
    if (!isDown) return;
    const x = e.pageX - slider.offsetLeft;
    const walk = x - startX;
    slider.scrollLeft = scrollLeft - walk;
  });

  const snapAndEndInteraction = () => {
    if (!isDown) return;
    isDown = false;
    slider.classList.remove("active-drag");
    slider.style.cursor = "grab";

    const slideWidth = slider.clientWidth;
    const currentScroll = slider.scrollLeft;
    const totalSlides = slider.children.length;
    const maxScrollLeft = (totalSlides - 1) * slideWidth;

    let activeSlideIndex = Math.round(currentScroll / slideWidth);
    activeSlideIndex = Math.max(0, Math.min(activeSlideIndex, totalSlides - 1));

    let targetScrollLeft = activeSlideIndex * slideWidth;

    if (activeSlideIndex === totalSlides - 1) {
      targetScrollLeft = maxScrollLeft;
    }

    slider.style.scrollBehavior = "smooth";
    slider.scrollTo({ left: targetScrollLeft });

    scheduleRestartAutoSlide();

    setTimeout(() => {
      slider.style.scrollBehavior = "auto";
    }, 600);
  };

  window.addEventListener("mouseup", snapAndEndInteraction);
  slider.addEventListener("mouseleave", () => {
    if (isDown) {
      snapAndEndInteraction();
    } else {
      scheduleRestartAutoSlide();
    }
  });

  const updateArrowState = () => {
    if (!prevBtn || !nextBtn) return;
    const slideWidth = slider.clientWidth;
    const totalSlides = slider.children.length;
    const currentIndex = Math.round(slider.scrollLeft / slideWidth);
    prevBtn.classList.toggle("hidden", currentIndex === 0);
    nextBtn.classList.toggle("hidden", currentIndex === totalSlides - 1);
  };

  if (prevBtn && nextBtn) {
    prevBtn.addEventListener("click", () => {
      stopAutoSlide();
      const slideWidth = slider.clientWidth;
      const currentScroll = slider.scrollLeft;
      let currentSlideIndex = Math.round(currentScroll / slideWidth);
      let targetSlideIndex = Math.max(0, currentSlideIndex - 1);
      slider.style.scrollBehavior = "smooth";
      slider.scrollTo({ left: targetSlideIndex * slideWidth });
      scheduleRestartAutoSlide();
      setTimeout(updateArrowState, 500);
    });

    nextBtn.addEventListener("click", () => {
      stopAutoSlide();
      const slideWidth = slider.clientWidth;
      const totalSlides = slider.children.length;
      const maxScrollLeft = (totalSlides - 1) * slideWidth;
      const currentScroll = slider.scrollLeft;
      let currentSlideIndex = Math.round(currentScroll / slideWidth);
      let targetSlideIndex = Math.min(currentSlideIndex + 1, totalSlides - 1);
      let targetScrollLeft = targetSlideIndex * slideWidth;
      targetScrollLeft = Math.min(targetScrollLeft, maxScrollLeft);

      slider.style.scrollBehavior = "smooth";
      slider.scrollTo({ left: targetScrollLeft });
      scheduleRestartAutoSlide();
      setTimeout(updateArrowState, 500);
    });

    slider.addEventListener("scroll", updateArrowState);
    updateArrowState();
  }

  if (isAutoSlideTarget()) {
    startAutoSlide();
    slider.addEventListener("mouseenter", () => {
      if (!isDown) {
        stopAutoSlide();
      }
    });
  }

  slider.style.cursor = "grab";
  slider.style.userSelect = "none";
  slider.style.webkitUserSelect = "none";
  slider.style.msUserSelect = "none";
  slider.addEventListener("mousedown", () => {
    slider.style.cursor = "grabbing";
  });
  slider.addEventListener("mouseup", () => {
    slider.style.cursor = "grab";
  });
});

const categoryContainers = document.querySelectorAll(
  ".category-item-container.scrolling"
);

categoryContainers.forEach((container) => {
  const wrapper = container.querySelector(".category-items-wrapper");
  if (!wrapper) return;
  const clone = wrapper.cloneNode(true);
  clone.setAttribute("aria-hidden", "true");
  wrapper.parentElement.appendChild(clone);

  const bannerDuration = getComputedStyle(document.documentElement)
    .getPropertyValue("--scroll-duration")
    .replace("s", "")
    .trim();

  const autoSlideInterval = Number(bannerDuration) * 2000;
});

const headerTop = document.querySelector(".header-top");
const navMenu = document.querySelector(".desktop-navigation-menu");
let lastScrollY = window.scrollY;

window.addEventListener("scroll", () => {
  const currentScrollY = window.scrollY;

  if (currentScrollY > 50) {
    headerTop?.classList.add("hide-top");
  } else {
    headerTop?.classList.remove("hide-top");
  }

  lastScrollY = currentScrollY;
});
document.addEventListener("DOMContentLoaded", () => {
  const modalOverlay = document.getElementById("globalModalOverlay");
  const modalContent = document.getElementById("globalModalContent");
  const modalIcon = document.getElementById("globalModalIcon");
  const modalMessage = document.getElementById("globalModalMessage");
  const modalCloseBtn = document.getElementById("globalModalCloseBtn");
  let modalTimeout;

  function showGlobalModal(message, type = "success", duration = 3000) {
    if (!modalContent || !modalOverlay || !modalIcon || !modalMessage) return;

    clearTimeout(modalTimeout);

    modalMessage.textContent = message;

    modalIcon.classList.remove("success", "error");
    modalIcon.classList.add(type);

    modalOverlay.classList.add("active");
    modalContent.classList.add("active");

    if (duration > 0) {
      modalTimeout = setTimeout(hideGlobalModal, duration);
    }
  }

  function hideGlobalModal() {
    if (modalOverlay && modalContent) {
      modalOverlay.classList.remove("active");
      modalContent.classList.remove("active");
    }
    clearTimeout(modalTimeout);
  }

  if (modalCloseBtn) modalCloseBtn.addEventListener("click", hideGlobalModal);
  if (modalOverlay) modalOverlay.addEventListener("click", hideGlobalModal);

  window.showGlobalModal = showGlobalModal;
});
