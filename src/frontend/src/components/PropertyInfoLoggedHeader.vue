<template>
  <header class="logged-header">
    <div class="logo">
      <router-link to="/"><img src="@/assets/logo.jpg" alt="StudentSpaceSolutions" /></router-link>
    </div>
    <!-- Search Bar -->
    <div  class="search-bar-container show">
      <input type="text" class="search-bar" placeholder="Search by city, suburb or university..." />
      <button class="search-icon">
        <font-awesome-icon icon="search" />
      </button>
    </div>
    <nav class="nav-links">
      <a href="#">Becoming a landlord</a>
      <a href="#">Inbox</a>
      <div class="user-profile" ref="userProfile" @click.stop="toggleDropdown">
        <img src="@/assets/me.jpg" alt="User"/>
        <span>Me</span>
        <div v-if="isDropdownVisible" class="dropdown-menu">
          <ul>
            <li><a href="#">Inbox</a></li>
            <li><a href="#">Account</a></li>
            <li><a href="#">Favourites</a></li>
            <li><a href="#">Logout</a></li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>
<script>
export default {
  name: "PropertyInfoLoggedHeader",
  data() {
    return {
      isDropdownVisible: false
    };
  },
  methods: {
    toggleDropdown() {
      this.isDropdownVisible = !this.isDropdownVisible;
    },
    handleOutsideClick(event) {
      const profileElement = this.$refs.userProfile;
      if (profileElement && !profileElement.contains(event.target)) {
        this.isDropdownVisible = false;
      }
    }
  },
  watch: {
    isDropdownVisible(newValue) {
      if (newValue) {
        document.addEventListener('click', this.handleOutsideClick);
      } else {
        document.removeEventListener('click', this.handleOutsideClick);
      }
    }
  },
  beforeUnmount() {
    // Clean up event listener when the component is destroyed
    document.removeEventListener('click', this.handleOutsideClick);
  }
};
</script>

<style scoped>
.logged-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.logo {
  font-size: 24px;
  font-weight: bold;
}


.search-bar-container {
  display: flex;
  align-items: center;
  position: sticky; /* Fixed position to stay at the top */
  top: 40px; /* Adjust based on header height */
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

.nav-links {
  display: flex;
  align-items: center;
}

.nav-links a {
  margin-right: 15px;
  color: #333;
  text-decoration: none;
}

.user-profile {
  display: flex;
  align-items: center;
  position: relative;
  cursor: pointer;
}

.user-profile img {
  border-radius: 50%;
  width: 40px;
  height: 40px;
  margin-right: 10px;
}

.user-profile span {
  font-size: 16px;
  font-weight: bold;
}

.dropdown-menu {
  position: absolute;
  top: 100%;
  right: 0;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  width: 400px; /* Adjust width as needed */
  z-index: 1000;
  display: block; /* For debugging, ensure the dropdown is visible */
}

.dropdown-menu ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.dropdown-menu li {
  border-bottom: 1px solid #ddd;
}

.dropdown-menu li:last-child {
  border-bottom: none;
}

.dropdown-menu a {
  display: block;
  padding: 14px;
  text-decoration: none;
  color: #333;
}

.dropdown-menu a:hover {
  background-color: #f0f0f0;
  color: #00796b;
}
</style>
