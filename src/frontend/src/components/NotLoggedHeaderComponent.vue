<template>
  <header class="not-logged-header">
    <div class="logo">
      <router-link to="/"><img src="@/assets/logo.jpg" alt="StudentSpaceSolutions" /></router-link>
    </div>
    <nav class="nav-links">
      <router-link to="/become-landlord">Becoming a landlord</router-link>
      <div class="signup-container">
        <router-link to="#" @click.prevent="showLogin">Sign Up</router-link>
      </div>
      <router-link to="#" @click.prevent="showLogin">Login</router-link>
    </nav>
    <!-- Search Bar -->
    <div v-if="showSearchBar" class="search-bar-container show">
      <input type="text" class="search-bar" placeholder="Search by city, suburb or university..." />
      <button class="search-icon">
        <font-awesome-icon icon="search" />
      </button>
    </div>
    <!-- Login Popup -->
    <div v-if="loginVisible" class="overlay" @click="hideLogin">
      <div class="login-popup"  @click.stop>
        <LoginPage @authenticated="handleAuthentication" @close="hideLogin" />
      </div>
    </div>
  </header>
</template>

<script>
import LoginPage from "@/components/LoginPage.vue";

export default {
  name: "NotLoggedHeaderComponent",
  components: {
    LoginPage
  },
  data() {
    return {
      showSearchBar: false,
      loginVisible: false
    };
  },
  methods: {
    handleScroll() {
      this.showSearchBar = window.scrollY > 100; // Adjust scroll position as needed
    },
    showLogin() {
      this.loginVisible = true;

    },
    hideLogin() {
      this.loginVisible = false;
      document.removeEventListener('click', this.handleOutsideClick);
    },
  handleAuthentication() {
    this.$emit('authenticated');
    this.hideLogin();
  },
    handleOutsideClick(event) {
      const loginPopupElement = this.$el.querySelector('.login-popup');
      if (loginPopupElement && !loginPopupElement.contains(event.target)) {
        this.hideLogin();
      }
    }
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll);
    document.addEventListener('click', this.handleOutsideClick);
  },
  beforeUnmount() {
    window.removeEventListener('scroll', this.handleScroll);

  }
};
</script>

<style scoped>
.not-logged-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  position: fixed; /* Fixed position to make it sticky */
  top: 0;
  width: 100%;
  z-index: 1000; /* Ensure it stays above other content */
}

.logo img {
  height: 50px;
}

.nav-links {
  display: flex;
  align-items: center;
  gap: 20px;
  margin-left: auto; /* Push links to the right */
}

.nav-links a {
  color: #333;
  text-decoration: none;
  font-weight: bold;
  transition: color 0.3s ease;
}

.signup-container {
  background-color: #ff4081;
  border-radius: 20px;
  padding: 5px 15px;
}

.signup-container a {
  color: white;
}

.signup-container a:hover {
  text-decoration: none;
}

/* Search Bar Styles */
.search-bar-container {
  display: flex;
  align-items: center;
  position: fixed; /* Fixed position to stay at the top */
  top: 20px; /* Adjust based on header height */
  left: 50%;
  transform: translateX(-50%);
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 20px;
  padding: 5px;
  transition: opacity 0.3s ease, transform 0.3s ease;
  opacity: 0;
  pointer-events: none;
  width: 600px; /* Adjust width as needed */
}

.search-bar-container.show {
  opacity: 1;
  pointer-events: auto;
}

.search-bar {
  border: 1px solid #ccc;
  border-radius: 20px;
  padding: 10px;
  padding-right: 40px; /* Space for the search icon */
  font-size: 16px;
  width: 600px;
}

.search-icon {
  background-color: #ff4081;
  border: none;
  border-radius: 50%;
  color: white;
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  right: 10px;
  cursor: pointer;
}

/* Login Popup Styles */
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5); /* Darkened background */
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 2000; /* Ensure it sits above other content */
}

.login-popup {
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  width: 400px; /* Adjust width as needed */
  max-width: 90%;
}
</style>
