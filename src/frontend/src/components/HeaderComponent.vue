<template>
  <header class="header">
    <div class="logo">
      <router-link to="/"><img src="@/assets/logo.jpg" alt="StudentSpaceSolutions"/></router-link>
    </div>
    <nav class="nav">
      <ul>
        <li>
          <router-link to="/">Home</router-link>
        </li>
        <li><a href="#">Student Accommodation</a></li>
        <li>
          <router-link to="/contact">Contact Us</router-link>
        </li>
        <li><a href="#">Becoming a landlord</a></li>
        <li><a href="#">FAQ</a></li>
      </ul>
    </nav>
    <div class="user-profile"  @click.stop="toggleDropdown">
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
  </header>
</template>

<script>
export default {
  name: "HeaderComponent",
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
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: white;
}

.logo img {
  width: 200px;
}

.nav ul {
  display: flex;
  list-style-type: none;
  padding: 0;
}

.nav li {
  margin-right: 20px;
}

.nav a {
  text-decoration: none;
  color: #333;
  font-size: 16px;
}

.nav a:hover {
  color: #00796b;
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
