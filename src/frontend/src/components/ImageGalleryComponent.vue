<template>
  <div class="image-gallery">
    <div class="main-image">
      <img src="@/assets/accomodation2.jpg" alt="Main Image" />
    </div>
    <div class="thumbnail-grid">
      <img src="@/assets/accomodation1.jpg" alt="Thumbnail 1" />
      <img src="@/assets/bedroom1.jpg" alt="Thumbnail 2" />
      <img src="@/assets/view1.jpg" alt="Thumbnail 3" />
      <div class="thumbnail-container">
        <img src="@/assets/kitchen1.jpeg" alt="Thumbnail 4" />
        <div class="view-all">View all</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ImageGalleryComponent",
};
</script>

<style scoped>
.image-gallery {
  display: grid;
  grid-template-columns: 2fr 1fr; /* Main image takes up 2 parts, thumbnails take 1 part */
  grid-template-rows: 1fr 1fr; /* Two rows, same height */
  gap: 10px;
  height: 500px; /* Adjust as needed */
}

.main-image {
  grid-column: 1 / 2; /* First column */
  grid-row: 1 / 3; /* Spans both rows */
}

.main-image img {
  width: 100%;
  height: 100%;
  object-fit: cover; /* Cover the container without distortion */
  border-radius: 10px;
}

.thumbnail-grid {
  grid-column: 2 / 3; /* Second column */
  grid-row: 1 / 3; /* Spans both rows */
  display: grid;
  grid-template-columns: repeat(2, 1fr); /* Two columns */
  grid-template-rows: repeat(2, 1fr); /* Two rows */
  gap: 10px;
}

.thumbnail-grid img {
  width: 100%;
  height: 100%;
  object-fit: cover; /* Cover the container without distortion */
  border-radius: 10px;
}

.thumbnail-container {
  position: relative;
}

.view-all {
  position: absolute;
  right: 10px;
  bottom: 10px;
  background-color: #ff4081;
  color: white;
  text-align: center;
  padding: 5px 10px;
  border-radius: 10px;
  cursor: pointer;
  width: 80px;
  font-size: 14px;
}
</style>
