<template>
  <div class="student-application-list">
    <!-- Navbar -->
    <nav class="app-navbar">
      <img src="@/assets/image.png" alt="StudentSpaceSolutions" class="navbar-logo" />
      <ul class="navbar-links">
        <li><router-link to="/">Home</router-link></li>
        <li><router-link to="/accommodation">Student Accommodation</router-link></li>
        <li><router-link to="/contact">Contact Us</router-link></li>
        <li><router-link to="/faq">FAQ</router-link></li>
      </ul>
      <div class="profile-section">
        <div class="profile-pic"></div>
        <span>Me</span>
      </div>
    </nav>

    <div class="content-area">
      <!-- Sidebar -->
      <div class="app-sidebar">
        <ul class="sidebar-menu">
          <li><router-link to="/dashboard">Dashboard</router-link></li>
          <li><router-link to="/profile">Profile</router-link></li>
          <li><router-link to="/faq">FAQ</router-link></li>
          <li><router-link to="/applications">Applications</router-link></li>
          <li><router-link to="/contact">Contact Us</router-link></li>
        </ul>
        <img src="@/assets/image.png" alt="StudentSpaceSolutions" class="sidebar-logo" />
      </div>

      <!-- Application List -->
      <div class="application-list">
        <div class="application-item" v-for="application in applications" :key="application.appNo">
          <div class="left-column">
            <div class="landlord-info">
              <div class="landlord-image-placeholder"></div>
              <div class="landlord-details">
                <h4>Landlord:</h4>
                <p>{{ application.property.landlord.name.firstName + ' ' + application.property.landlord.name.lastName }}</p>
                <p><strong>Cell:</strong> {{ application.property.landlord.contact.phoneNumber }}</p>
                <p><strong>Email:</strong> <a :href="'mailto:' + application.property.landlord.contact.email">{{ application.property.landlord.contact.email }}</a></p>
                <p><strong>City:</strong> {{ application.property.landlord.contact.address.city }}</p>
              </div>
            </div>
          </div>
          <div class="right-column">
            <h2>{{ application.property.propertyName }}</h2>
            <p>{{ application.property.address.street + ', ' + application.property.address.suburb + ', ' + application.property.address.city }}</p>
            <p>R {{ application.price }}</p>
            <div :class="['status', application.status.toLowerCase()]">
              Status: {{ application.status }}
            </div>
            <button class="btn-withdraw" @click="confirmWithdraw(application.appNo)">Withdraw</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'StudentApplicationList',
  data() {
    return {
      applications: []
    };
  },
  mounted() {
    fetch('http://localhost:8081/StudentHomeBas/StudentApplication/getAll')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => {
        this.applications = data;
      })
      .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
      });
  },
  methods: {
    confirmWithdraw(appNo) {
      if (confirm('Are you sure you want to withdraw this application?')) {
        this.withdrawApplication(appNo);
      }
    },
    withdrawApplication(appNo) {
      fetch(`http://localhost:8081/StudentHomeBas/StudentApplication/delete/${appNo}`, {
        method: 'DELETE'
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        // Remove the application from the list after successful deletion
        this.applications = this.applications.filter(application => application.appNo !== appNo);
      })
      .catch(error => {
        console.error('There was a problem with the withdraw operation:', error);
      });
    }
  }
};
</script>

<style scoped>
/* Navbar styles */
.app-navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #fff;
  border-bottom: 1px solid #ccc;
}

.navbar-logo {
  height: 50px;
}

.navbar-links {
  list-style: none;
  display: flex;
  gap: 20px;
  margin-left: 20px; /* Move links slightly to the left */
}

.navbar-links li {
  font-size: 16px;
}

.navbar-links li a {
  text-decoration: none;
  color: #007b5e; /* Green color from your design */
  padding: 5px 10px;
  border-radius: 5px;
  transition: background-color 0.3s ease;
}

.navbar-links li a:hover {
  background-color: #e0f7f1; /* Light green background on hover */
  color: #004c3f; /* Darker green text color on hover */
}

.profile-section {
  display: flex;
  align-items: center;
}

.profile-pic {
  width: 40px;
  height: 40px;
  background-color: #ccc;
  border-radius: 50%;
  margin-right: 10px;
}

/* Sidebar styles */
.app-sidebar {
  width: 250px;
  padding: 20px;
  background-color: #007b5e; /* Green styling */
  display: flex;
  flex-direction: column;
  justify-content: space-between; /* Ensures the logo is at the bottom */
  height: 100vh;
}

.sidebar-logo {
  width: 100px; /* Adjust width as needed */
  height: auto;
  align-self: center;
  margin-top: auto; /* Pushes the logo to the bottom */
}

.sidebar-menu {
  list-style: none;
  padding: 0;
}

.sidebar-menu li {
  margin-bottom: 15px;
}

.sidebar-menu li a {
  color: #fff; /* Ensure text color is white */
  text-decoration: none;
  font-size: 16px;
}

.sidebar-menu li a:hover {
  text-decoration: underline;
}

/* Application List styles */
.application-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 20px;
  flex-grow: 1;
}

.application-item {
  border: 1px solid #ccc;
  padding: 20px;
  display: flex;
  flex-direction: row;
  gap: 20px;
}

.left-column {
  flex: 1;
  display: flex;
  align-items: center;
}

.landlord-info {
  display: flex;
  flex-direction: row;
  gap: 20px;
  align-items: center;
}

.landlord-image-placeholder {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  background-color: #ccc; /* Gray color for the placeholder */
}

.landlord-details {
  flex: 1;
}

.right-column {
  flex: 2;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.right-column h2 {
  color: #333;
}

.status {
  margin-top: 10px;
  font-weight: bold;
  display: inline-block;
  padding: 5px 10px;
  border-radius: 5px;
}

.status.pending {
  background-color: #ffcc00;
  color: #000;
}

.status.accepted {
  background-color: #4caf50;
  color: #fff;
}

.btn-withdraw {
  background-color: red;
  color: white;
  border: none;
  padding: 10px 15px;
  cursor: pointer;
  border-radius: 5px;
  margin-top: 10px;
}

.btn-withdraw:hover {
  background-color: darkred;
}

/* Layout adjustments */
.content-area {
  display: flex;
  flex-direction: row;
}
</style>
