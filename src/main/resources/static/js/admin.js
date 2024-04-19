const modalPost = document.querySelector(".modal_");
const overlayPost = document.querySelector(".overlay");
const showModals = document.querySelectorAll(".show-modal");

for (let i = 0; i < showModals.length; i++) {
  showModals[i].addEventListener("click", function () {
    showModal(modalPost, overlayPost);
  });
}

function showModal(modal, overlay) {
  modal.classList.remove("hidden");
  overlay.classList.remove("hidden");
}

overlayPost.addEventListener("click", function () {
  hideModal(modalPost, overlayPost);
});

function hideModal(modal, overlay) {
  modal.classList.add("hidden");
  overlay.classList.add("hidden");
}

document.addEventListener("keydown", function (event) {
  if (event.key === "Escape") {
    if (!modalPost.classList.contains("hidden")) {
      hideModal(modalPost, overlayPost);
    }
  }
});
