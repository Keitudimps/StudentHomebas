<template>
  <div class="apartment-details">
    <h1>Stylish Apartments in Newlands (All Inclusive)</h1>
    <h2>Apartment</h2>
    <p class="sub-description">Stylish, Great community & Unique</p>
    <p class="location">Newlands</p>
    <div class="details-icons">
      <span> <font-awesome-icon icon="wifi" /> Room by room leases</span>
      <span> <font-awesome-icon icon="couch" /> Furnished</span>
      <span> <font-awesome-icon icon="restroom" /> Max occupants: 244</span>
    </div>
    <p class="description" :class="{ expanded: isExpanded }">
      Fully furnished, all inclusive co-living suites in Newlands, at upcoming Neighbourgood Newlands! Whether you are
      seeking a fully furnished accommodation or an all inclusive workspace...
      <span v-if="!isExpanded">...<button @click="toggleDescription" class="read-more-btn">Read more</button></span>
      <span v-if="isExpanded">
        <br />This is the additional content that is only shown when 'Read more' is clicked.
        It includes more details about the apartment, amenities, and other important information.
        <button @click="toggleDescription" class="read-more-btn">Read less</button>
      </span>
    </p>
  </div>
</template>

<script>
export default {
  name: "ApartmentDetailsComponent",
  data() {
    return {
      isExpanded: false,
    };
  },
  methods: {
    toggleDescription() {
      this.isExpanded = !this.isExpanded;
    },
  },
};
</script>

<style scoped>
.apartment-details {
  padding: 20px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h1 {
  font-size: 24px;
  font-weight: bold;
}

h2 {
  font-size: 18px;
  color: #666;
}

.sub-description {
  color: #ff4081;
  font-weight: bold;
  margin-top: 10px;
}

.location {
  margin-top: 10px;
  font-size: 16px;
  color: #333;
}

.details-icons {
  margin: 20px 0;
  display: flex;
  gap: 20px;
}

.details-icons span {
  display: flex;
  align-items: center;
  font-size: 14px;
  color: #333;
  margin-right: 20px;
}

.description {
  color: #666;
  overflow: hidden;
  max-height: 80px;
  transition: max-height 0.3s ease;
}

.description.expanded {
  max-height: none;
}

.read-more-btn {
  padding: 10px 20px;
  background-color: #ff4081;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 10px;
}

.read-more-btn:hover {
  background-color: #e91e63;
}
</style>
