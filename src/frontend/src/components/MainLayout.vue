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
      <aside class="text-white p-4 sidebar" style="width: 250px;">
        <ul class="nav flex-column">
          <li class="nav-item mb-3">
            <router-link class="nav-link text-white" to="/" exact-active-class="active">
              <i class="bi bi-grid"></i> Dashboard
            </router-link>
          </li>
          <li class="nav-item mb-3">
            <router-link class="nav-link text-white" to="/my-properties" exact-active-class="active">
              <i class="bi bi-house"></i> My Properties
            </router-link>
          </li>
          <li class="nav-item mb-3">
            <router-link class="nav-link text-white" to="/register-property" exact-active-class="active">
              <i class="bi bi-plus-circle"></i> Register Property
            </router-link>
          </li>
          <li class="nav-item mb-3">
            <router-link class="nav-link text-white" to="/landlord-inbox" exact-active-class="active">
              <i class="bi bi-people"></i> Landlord Inbox
            </router-link>
          </li>
          <li class="nav-item mb-3">
            <router-link class="nav-link text-white" to="/student-inbox" exact-active-class="active">
              <i class="bi bi-people"></i> Student Inbox
            </router-link>
          </li>
        </ul>
      </aside>
      <main class="content p-4" style="flex: 1;" >
        <router-view></router-view>
      </main>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style scoped>


.sidebar {
  width: 250px;
  background-image: url('@/assets/bedroom.png');
  background-size: contain;
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
  background-color: rgba(0, 0, 0, 0.3); /* Adjust the opacity here */
  z-index: 1;
}

/* Ensure that the content within the sidebar is above the overlay */
.sidebar > * {
  position: relative;
  z-index: 2;
}


.nav-link {
  background-color: #127670; /* Default background color */
  padding: 12px 15px;
  border-radius: 4px;
  transition: background-color 0.3s ease;
}

.nav-link:hover {
  background-color: #1F2323; /* Hover background color */
}

.nav-link.active {
  background-color: #1F2323 !important; /* Active background color */
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
