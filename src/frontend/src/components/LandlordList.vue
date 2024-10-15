<template>
  <div class="landlord-list custom-background">
    <h1 class="title">Landlord List</h1>
    <p v-if="error" class="error">{{ error }}</p>
    <table v-if="landlords.length" class="landlord-table">
      <thead>
      <tr>
        <th>Name</th>
        <th>Gender</th>
        <th>Date of Birth</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Property Count</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="landlord in landlords" :key="landlord.userId">
        <td>{{ landlord.name ? landlord.name.firstName + ' ' + landlord.name.lastName : 'No name available' }}</td>
        <td>{{ landlord.gender || 'No gender available' }}</td>
        <td>{{ landlord.dateOfBirth || 'No date available' }}</td>
        <td>{{ landlord.contact ? landlord.contact.email : 'No email available' }}</td>
        <td>{{ landlord.contact ? landlord.contact.phoneNumber : 'No phone number available' }}</td>
        <td>{{ landlord.propertyCount || 'No property count available' }}</td>
        <td>
          <button @click="openEditDialog(landlord)" class="edit-button">Edit</button>
          <button @click="deleteLandlord(landlord.userId)" class="delete-button">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>
    <p v-else>No landlords available.</p>

    <!-- Edit Dialog -->
    <div v-if="showEditDialog" class="edit-dialog">
      <div class="dialog-content">
        <h2>Edit Landlord</h2>
        <form @submit.prevent="updateLandlord">
          <label>
            First Name:
            <input v-model="editedLandlord.name.firstName" required />
          </label>
          <label>
            Last Name:
            <input v-model="editedLandlord.name.lastName" required />
          </label>
          <label>
            Gender:
            <input v-model="editedLandlord.gender" required />
          </label>
          <label>
            Date of Birth:
            <input type="date" v-model="editedLandlord.dateOfBirth" required />
          </label>
          <label>
            Email:
            <input v-model="editedLandlord.contact.email" required />
          </label>
          <label>
            Phone:
            <input v-model="editedLandlord.contact.phoneNumber" required />
          </label>
          <label>
            Property Count:
            <input type="number" v-model="editedLandlord.propertyCount" required />
          </label>
          <button type="submit" class="save-button">Save</button>
          <button type="button" @click="closeEditDialog" class="cancel-button">Cancel</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LandlordList',
  data() {
    return {
      landlords: [],
      error: null,
      showEditDialog: false,
      editedLandlord: null
    };
  },
  mounted() {
    this.fetchLandlords();
  },
  methods: {
    async fetchLandlords() {
      try {
        const response = await fetch("api/StudentHomeBas/landlord/getall");
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        this.landlords = await response.json();
      } catch (error) {
        this.error = 'Error fetching data';
        console.error('There was a problem with the fetch operation:', error);
      }
    },
    openEditDialog(landlord) {
      this.editedLandlord = { ...landlord };
      this.showEditDialog = true;
    },
    closeEditDialog() {
      this.showEditDialog = false;
      this.editedLandlord = null;
    },
    async updateLandlord() {
      try {
        const response = await fetch(`api/StudentHomeBas/landlord/update`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.editedLandlord)
        });
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        await this.fetchLandlords();
        this.closeEditDialog();
      } catch (error) {
        this.error = `Error updating landlord: ${error.message}`;
        console.error('There was a problem with the update operation:', error);
      }
    },
    async deleteLandlord(userId) {
      if (confirm(`Are you sure you want to delete this landlord? ${userId}`)) {
        try {
          const response = await fetch(`api/StudentHomeBas/landlord/delete/${userId}`, {
            method: 'DELETE'
          });
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          await this.fetchLandlords();
        } catch (error) {
          this.error = `Error deleting landlord: ${error.message}`;
          console.error('There was a problem with the delete operation:', error);
        }
      }
    }
  }
}
</script>

<style scoped>
.custom-background {
  background-color: #F9F9F9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
}

.error {
  color: red;
  margin-bottom: 20px;
}

.landlord-table {
  width: 100%;
  border-collapse: collapse;
}

.landlord-table th, .landlord-table td {
  padding: 12px;
  text-align: left;
  border: 1px solid #ddd;
}

.landlord-table th {
  background-color: #127670;
  color: white;
}

.landlord-table tr:nth-child(even) {
  background-color: #f2f2f2;
}

.details-link {
  color: #127670;
  text-decoration: none;
}

.details-link:hover {
  text-decoration: underline;
}

.edit-button {
  color: #127670;
  background: none;
  border: none;
  cursor: pointer;
  font-weight: bold;
}

.edit-button:hover {
  text-decoration: underline;
}

.delete-button {
  color: red;
  background: none;
  border: none;
  cursor: pointer;
  font-weight: bold;
}

.delete-button:hover {
  text-decoration: underline;
}

/* Edit Dialog Styles */
.edit-dialog {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.dialog-content {
  background: white;
  padding: 20px;
  border-radius: 8px;
  width: 80%;
  max-width: 600px;
}

.dialog-content form {
  display: flex;
  flex-direction: column;
}

.dialog-content label {
  margin-bottom: 10px;
}

.dialog-content input {
  padding: 8px;
  margin-top: 5px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.save-button {
  background-color: #127670;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 10px;
}

.save-button:hover {
  background-color: #105a54;
}

.cancel-button {
  background-color: #f44336;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 10px;
  margin-left: 10px;
}

.cancel-button:hover {
  background-color: #c62828;
}
</style>
