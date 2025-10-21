"use strict";

// modal variables
const modal = document.querySelector("[data-modal]");
const modalCloseBtn = document.querySelector("[data-modal-close]");
const modalCloseOverlay = document.querySelector("[data-modal-overlay]");

// modal function
const modalCloseFunc = function () {
  modal.classList.add("closed");
};

// modal eventListener
modalCloseOverlay.addEventListener("click", modalCloseFunc);
modalCloseBtn.addEventListener("click", modalCloseFunc);

// notification toast variables
const notificationToast = document.querySelector("[data-toast]");
const toastCloseBtn = document.querySelector("[data-toast-close]");

// notification toast eventListener
toastCloseBtn.addEventListener("click", function () {
  notificationToast.classList.add("closed");
});

// mobile menu variables
const mobileMenuOpenBtn = document.querySelectorAll(
  "[data-mobile-menu-open-btn]"
);
const mobileMenu = document.querySelectorAll("[data-mobile-menu]");
const mobileMenuCloseBtn = document.querySelectorAll(
  "[data-mobile-menu-close-btn]"
);
const overlay = document.querySelector("[data-overlay]");

for (let i = 0; i < mobileMenuOpenBtn.length; i++) {
  // mobile menu function
  const mobileMenuCloseFunc = function () {
    mobileMenu[i].classList.remove("active");
    overlay.classList.remove("active");
  };

  mobileMenuOpenBtn[i].addEventListener("click", function () {
    mobileMenu[i].classList.add("active");
    overlay.classList.add("active");
  });

  mobileMenuCloseBtn[i].addEventListener("click", mobileMenuCloseFunc);
  overlay.addEventListener("click", mobileMenuCloseFunc);
}

// accordion variables
const accordionBtn = document.querySelectorAll("[data-accordion-btn]");
const accordion = document.querySelectorAll("[data-accordion]");

for (let i = 0; i < accordionBtn.length; i++) {
  accordionBtn[i].addEventListener("click", function () {
    const clickedBtn = this.nextElementSibling.classList.contains("active");

    for (let i = 0; i < accordion.length; i++) {
      if (clickedBtn) break;

      if (accordion[i].classList.contains("active")) {
        accordion[i].classList.remove("active");
        accordionBtn[i].classList.remove("active");
      }
    }

    this.nextElementSibling.classList.toggle("active");
    this.classList.toggle("active");
  });
}

// scroll bar
const sliders = document.querySelectorAll(
  ".slider-container, .showcase-wrapper"
);
const autoSlideInterval = 1800;

sliders.forEach((slider) => {
  let isDown = false;
  let startX;
  let scrollLeft;
  let autoSlideTimer = null;
  let interactionTimeout = null;

  slider.style.scrollSnapType = "none";
  slider.style.scrollBehavior = "auto";

  const stopAutoSlideIfNeeded = () => {
    if (autoSlideTimer && slider.classList.contains("slider-container")) {
      clearInterval(autoSlideTimer);
      autoSlideTimer = null;
    }
  };

  const startAutoSlideIfNeeded = () => {
    if (slider.classList.contains("slider-container") && !autoSlideTimer) {
      clearTimeout(interactionTimeout);
      interactionTimeout = setTimeout(() => {
        startAutoSlide();
      }, autoSlideInterval);
    }
  };

  slider.addEventListener("mousedown", (e) => {
    isDown = true;
    slider.classList.add("active-drag");
    startX = e.pageX - slider.offsetLeft;
    scrollLeft = slider.scrollLeft;
    stopAutoSlideIfNeeded();
    clearTimeout(interactionTimeout);
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

    let targetScrollLeft;

    if (Math.abs(currentScroll - maxScrollLeft) < slideWidth / 3) {
      targetScrollLeft = maxScrollLeft;
    } else {
      let activeSlideIndex = Math.round(currentScroll / slideWidth);
      activeSlideIndex = Math.max(
        0,
        Math.min(activeSlideIndex, totalSlides - 1)
      );
      targetScrollLeft = activeSlideIndex * slideWidth;
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
    } else if (
      slider.classList.contains("slider-container") &&
      !autoSlideTimer
    ) {
      startAutoSlideIfNeeded();
    }
  });

  const startAutoSlide = () => {
    if (!slider.classList.contains("slider-container")) return;
    stopAutoSlideIfNeeded();
    clearTimeout(interactionTimeout);

    autoSlideTimer = setInterval(() => {
      const currentScroll = slider.scrollLeft;
      const slideWidth = slider.clientWidth;
      const totalSlides = slider.children.length;
      const maxScrollLeft = slider.scrollWidth - slideWidth;

      let nextScrollLeft = currentScroll + slideWidth;

      let currentNearestSlideIndex = Math.round(currentScroll / slideWidth);
      let nextTargetIndex = (currentNearestSlideIndex + 1) % totalSlides;
      let targetScrollLeft = nextTargetIndex * slideWidth;

      targetScrollLeft = Math.min(targetScrollLeft, maxScrollLeft);

      if (
        Math.abs(currentScroll - maxScrollLeft) < 10 &&
        nextTargetIndex !== 0
      ) {
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

  if (slider.classList.contains("slider-container")) {
    startAutoSlide();
    slider.addEventListener("mouseenter", () => {
      if (!isDown) {
        stopAutoSlideIfNeeded();
        clearTimeout(interactionTimeout);
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

// Tạo hiệu ứng trôi mượt liền mạch cho category
const categoryContainers = document.querySelectorAll(
  ".category-item-container.scrolling"
);

categoryContainers.forEach((container) => {
  const wrapper = container.querySelector(".category-items-wrapper");
  const clone = wrapper.cloneNode(true);
  wrapper.parentElement.appendChild(clone);
});
