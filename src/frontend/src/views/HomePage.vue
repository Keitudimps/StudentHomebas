<template>
  <div class="home">
    <!-- Existing content -->
    <NotLoggedHeaderComponent />
    <div class="search-section">
      <SearchBar />
    </div>
    <PropertyList />
    <FooterComponent />

    <!-- Button to trigger the login form display -->
    <button @click="showLoginForm" class="login-button">Login</button>

    <!-- Login form overlay -->
    <div v-if="showLogin" class="login-overlay">
      <div class="login-container">
        <button class="close-button" @click="showLogin = false">✖</button>
        <!-- Use the provided login form component -->
        <LoginPage />
      </div>
    </div>
  </div>
</template>

<script>
import NotLoggedHeaderComponent from "@/components/NotLoggedHeaderComponent.vue";
import SearchBar from "@/components/SearchBar.vue";
import PropertyList from "@/components/PropertyList.vue";
import FooterComponent from "@/components/FooterComponent.vue";
import LoginPage from "@/components/LoginPage.vue"; // Import the LoginPage component

export default {
  name: "HomePage",
  components: {
    NotLoggedHeaderComponent,
    SearchBar,
    PropertyList,
    FooterComponent,
    LoginPage
  },
  data() {
    return {
      showLogin: false // Control visibility of the login form
    };
  },
  methods: {
    showLoginForm() {
      this.showLogin = true;
    }
  }
};
</script>

<style scoped>
/* Existing styles */
.search-section {
  background:
      linear-gradient(
          rgba(0, 0, 0, 0.5), /* Dark overlay with 50% opacity */
          rgba(0, 0, 0, 0.5)  /* Same color for a uniform overlay */
      ),
      url('@/assets/property1.jpg'); /* Background image */
  background-color: black;
  background-size: cover; /* Ensure the image covers the section */
  background-position: center; /* Center the background image */
  padding: 50px 0;
}

/* Styles for the login overlay */
.login-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5); /* Darkened background */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.login-container {
  background-color: white;
  padding: 40px;
  border-radius: 8px;
  width: 100%;
  max-width: 400px; /* Adjust as needed */
  position: relative;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  background: transparent;
  border: none;
  font-size: 20px;
  cursor: pointer;
}

/* Button to trigger login */
.login-button {
  position: fixed;
  top: 20px;
  right: 20px;
  padding: 10px 20px;
  background-color: #009688;
  border: none;
  color: white;
  border-radius: 4px;
  cursor: pointer;
}
</style>
