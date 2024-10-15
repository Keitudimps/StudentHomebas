<template>
  <div class="property-card">
    <router-link to="/propertyInfo" class="no-underline">
    <img :src="image" alt="Property Image" />
    <div class="property-card__heart-icon">
      <font-awesome-icon icon="heart" />
    </div>
    <div class="property-details">
      <h3>{{ location }}</h3>
      <!-- Type or Apartment Type in Red -->
      <p class="property-type">{{ type }} - {{ roomTypes }}</p>
      <!-- Description in Bold -->
      <p class="property-description">{{ description }}</p>
      <p>Available {{ availableFrom }}</p>
      <!-- Price in Bold -->
      <p class="property-price">From {{ price }}/month</p>
      <div class="icons">
        <font-awesome-icon icon="home" />
        <font-awesome-icon icon="wifi" />
        <font-awesome-icon icon="bed" />
        <font-awesome-icon icon="restroom" />
      </div>
    </div>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "PropertyCard",
  props: {
    image: String,
    location: String,
    type: String,
    roomTypes: String,
    description: String,
    availableFrom: String,
    price: String
  }
};
</script>

<style scoped>
.no-underline {
  text-decoration: none;
  color: inherit; /* Ensure that the text color stays consistent */
}
.property-card {
  position: relative;
  border: 1px solid #ccc;
  border-radius: 20px;
  overflow: hidden;
  width: 400px;
  margin: 15px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s;
}

.property-card__heart-icon {
  position: absolute;
  top: 8px;
  right: 8px;
  font-size: 30px;
  color: rgba(0, 0, 0, 0.38);
  background-color: white;
  border-radius: 50%;
  padding: 4px;
  box-sizing: content-box;
  cursor: pointer;
}

.property-card__heart-icon.active {
  color: red;
}

.property-card__heart-icon:hover {
  color: red;
}

.property-card img {
  width: 100%;
  height: 250px;
  object-fit: cover;
}

.property-card:hover {
  transform: scale(1.05);
}

.property-details {
  padding: 20px;
  background-color: white;
}

.property-details h3 {
  margin-top: 0;
  color: #00796b;
  font-size: 20px;
}

.property-details p {
  margin: 8px 0;
  color: #333;
  font-size: 16px;
}

/* Type or Apartment Type in Red */
.property-type {
  color: red;
  font-size: 16px;
}

/* Description in Bold */
.property-description {
  font-weight: bold;
  font-size: 16px;
}

/* Price in Bold */
.property-price {
  font-weight: bold;
  font-size: 16px;
}
.icons {
  display: flex;
  justify-content: flex-end; /* Aligns icons to the right */
  gap: 10px; /* Space between icons */
  margin-top: 10px;
}

.icons span {
  font-size: 24px;
  color: #00796b;
}
</style>
