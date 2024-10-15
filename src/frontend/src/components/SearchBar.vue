<template>
  <div class="search-section">
    <div class="inner-container">
      <div class="search-container">
        <input
            type="text"
            placeholder="Search Property by Name, Type and Location"
            class="search-bar"
        />
        <div class="buttons-container">
          <button @click="toggleOptions" class="more-options-btn">
            More options <span :class="{'rotate': showOptions}">▼</span>
          </button>
          <button class="search-btn">Search</button>
        </div>
      </div>

      <!-- Back to using v-if -->
      <transition name="slide-fade">
        <div v-if="showOptions" class="additional-options">
          <div class="filter">
            <label for="property-type">Property Type:</label>
            <select id="property-type" v-model="propertyType">
              <option>Select property type</option>
              <option>House</option>
              <option>Apartment</option>
              <!-- Add more options as needed -->
            </select>
          </div>
          <div class="filter">
            <label for="price-from">Price From:</label>
            <input type="number" id="price-from" v-model="priceFrom" placeholder="3000" />
          </div>
          <div class="filter">
            <label for="price-to">To:</label>
            <input type="number" id="price-to" v-model="priceTo" placeholder="5000" />
          </div>
          <div class="filter">
            <label for="bedrooms">Bedrooms:</label>
            <select id="bedrooms" v-model="bedrooms">
              <option>1 or more</option>
              <!-- Add more options as needed -->
            </select>
          </div>
          <div class="filter">
            <label for="bathrooms">Bathrooms:</label>
            <select id="bathrooms" v-model="bathrooms">
              <option>1 or more</option>
              <!-- Add more options as needed -->
            </select>
          </div>
        </div>
      </transition>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      showOptions: false,
      propertyType: '',
      priceFrom: '',
      priceTo: '',
      bedrooms: '',
      bathrooms: ''
    };
  },
  methods: {
    toggleOptions() {
      this.showOptions = !this.showOptions;
    }
  }
};
</script>

<style scoped>
.search-section {
  text-align: center;
  margin-top: 20px;
  padding: 20px;
  border-radius: 10px;
  background-color: #000000;
}

.inner-container {
  background-color: rgba(211, 211, 211, 0.8);
  padding: 20px;
  width: 80%;
  max-width: 1000px;
  height: auto;
  margin: 0 auto;
  box-sizing: border-box;
}

.search-container {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  padding: 20px;
}

.search-bar {
  width: 60%;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #000000;
  font-size: 16px;
}

.buttons-container {
  display: flex;
  align-items: center;
}

.more-options-btn {
  background-color: white;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
}

.more-options-btn .rotate {
  transform: rotate(180deg);
}

.search-btn {
  background-color: #00796b;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  margin-left: 10px;
}

/* New transition effect */
.slide-fade-enter-active, .slide-fade-leave-active {
  transition: max-height 0.5s ease-in-out, opacity 0.5s ease-in-out;
}

.slide-fade-enter, .slide-fade-leave-to {
  max-height: 0;
  opacity: 0;
}

.additional-options {
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin-top: 20px;
  width: 100%;
  max-height: 500px; /* This is an example value */
  opacity: 1;
  overflow: hidden;
}

.filter {
  margin: 0 10px;
}

.filter label {
  display: block;
  font-weight: bold;
  margin-bottom: 5px;
}

.filter select, .filter input {
  width: 100%;
  padding: 8px;
  border-radius: 5px;
  border: 1px solid #ccc;
}
</style>
