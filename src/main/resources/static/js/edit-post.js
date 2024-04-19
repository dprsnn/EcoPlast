const modalParagraphs = document.querySelector(".modal_paragraphs");
const modalVideos = document.querySelector(".modal_videos");

const overlayParagraphs = document.querySelector(".overlay_paragraphs");
const overlayVideos = document.querySelector(".overlay_videos");

const showParagraphsModal = document.querySelector(".show-paragraphs_modal");
const showVideosModal = document.querySelector(".show-videos_modal");

showParagraphsModal.addEventListener("click", function () {
  showModal(modalParagraphs, overlayParagraphs);
});

showVideosModal.addEventListener("click", function () {
  showModal(modalVideos, overlayVideos);
});

function showModal(modal, overlay) {
  modal.classList.remove("hidden");
  overlay.classList.remove("hidden");
}

overlayParagraphs.addEventListener("click", function () {
  hideModal(modalParagraphs, overlayParagraphs);
});
overlayVideos.addEventListener("click", function () {
  hideModal(modalVideos, overlayVideos);
});

function hideModal(modal, overlay) {
  modal.classList.add("hidden");
  overlay.classList.add("hidden");
}

document.addEventListener("keydown", function (event) {
  if (event.key === "Escape") {
    if (!modalParagraphs.classList.contains("hidden")) {
      hideModal(modalParagraphs, overlayParagraphs);
    } else if (!modalVideos.classList.contains("hidden")) {
      hideModal(modalVideos, overlayVideos);
    }
  }
});
