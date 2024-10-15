<template>
  <div id="app" class="app-background">
    <header class="bg-light p-3 border-bottom">
      <div class="container d-flex justify-content-between align-items-center">
        <div class="d-flex align-items-center">
          <img src="path_to_logo" alt="StudentSpaceSolutions" style="width: 150px;">
        </div>
        <div class="d-flex align-items-center">
          <a href="#" class="me-3">Home</a>
          <a href="#" class="me-3">Contact Us</a>
          <div class="dropdown">
            <a class="d-flex align-items-center text-decoration-none dropdown-toggle" href="#" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
              <img src="path_to_user_image" alt="User" style="width: 40px; height: 40px; border-radius: 50%;">
              <span class="ms-2">Me</span>
            </a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownUser1">
              <li><a class="dropdown-item" href="#">Profile</a></li>
              <li><a class="dropdown-item" href="#">Logout</a></li>
            </ul>
          </div>
        </div>
      </div>
    </header>

    <div class="d-flex">
      <aside class="text-white p-4 sidebar">
        <ul class="nav flex-column">
          <li class="nav-item mb-3">
            <router-link class="nav-link text-white" to="/admin-dashboard" exact-active-class="active">
              <i class="fas fa-tachometer-alt"></i> Dashboard
            </router-link>
          </li>
          <li class="nav-item mb-3">
            <router-link class="nav-link text-white" to="/students" exact-active-class="active">
              <i class="fas fa-user-graduate"></i> Student details
            </router-link>
          </li>
          <li class="nav-item mb-3">
            <router-link class="nav-link text-white" to="/properties" exact-active-class="active">
              <i class="fas fa-building"></i> Property details
            </router-link>
          </li>
          <li class="nav-item mb-3">
            <router-link class="nav-link text-white" to="/landlords" exact-active-class="active">
              <i class="fas fa-user-tie"></i> Landlord Details
            </router-link>
          </li>
          <li class="nav-item mb-3">
            <router-link class="nav-link text-white" to="/communication" exact-active-class="active">
              <i class="fas fa-comments"></i> Communication
            </router-link>
          </li>
        </ul>
      </aside>
      <main class="content p-4" style="flex: 1;">
        <router-view></router-view>
      </main>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AdminLayout',
};
</script>

<style scoped>
.app-background {
  background-color: #F0F0F0; /* Light grey background for the whole app */
  background-image: url('@/assets/bedroom.png');
  min-height: 100vh;
}

.header {
  background-color: #f8f9fa;
}

.sidebar {
  background-color: #343a40; /* Dark background for sidebar */
  background-image: url('@/assets/bedroom.png');
  background-size: cover;
  background-repeat: no-repeat;
  background-attachment: fixed;
  min-height: 100vh;
  position: relative;
}

.sidebar::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.3); /* Overlay color */
  z-index: 1;
}

.sidebar > * {
  position: relative;
  z-index: 2;
}

.nav-link {
  background-color: #127670;
  padding: 12px 15px;
  border-radius: 4px;
  transition: background-color 0.3s ease;
}

.nav-link:hover {
  background-color: #1F2323;
}

.nav-link.active {
  background-color: #1F2323 !important;
}

.nav-link i {
  margin-right: 8px;
}

.content {
  background: transparent;
}

@media (max-width: 768px) {
  .sidebar {
    width: 100%;
    position: relative;
    background-size: cover;
  }

  .content {
    margin-left: 0;
  }

  header .container {
    flex-direction: column;
    align-items: flex-start;
  }

  header .container .d-flex {
    flex-direction: column;
    align-items: flex-start;
  }
}
</style>
