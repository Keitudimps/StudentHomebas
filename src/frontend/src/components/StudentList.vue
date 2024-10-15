<template>
  <div class="student-list custom-background">
    <h1 class="title">Student List</h1>
    <p v-if="error" class="error">{{ error }}</p>
    <table v-if="students.length" class="student-table">
      <thead>
      <tr>
        <th>Name</th>
        <th>Gender</th>
        <th>Date of Birth</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Academic Institute</th>
        <th>Program of Study</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="student in students" :key="student.userId">
        <td>{{ student.name ? student.name.firstName + ' ' + student.name.lastName : 'No name available' }}</td>
        <td>{{ student.gender || 'No gender available' }}</td>
        <td>{{ student.dateOfBirth || 'No date available' }}</td>
        <td>{{ student.contact ? student.contact.email : 'No email available' }}</td>
        <td>{{ student.contact ? student.contact.phoneNumber : 'No phone number available' }}</td>
        <td>{{ student.academicDetails ? student.academicDetails.instituteName : 'No institute available' }}</td>
        <td>{{ student.academicDetails ? student.academicDetails.programOfStudy : 'No program available' }}</td>
        <td class="action-buttons">
          <button @click="openEditDialog(student)" class="edit-button">Edit</button>
          <button @click="deleteStudent(student.userId)" class="delete-button">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>
    <p v-else>No students available.</p>

    <!-- Edit Dialog -->
    <div v-if="showEditDialog" class="edit-dialog">
      <div class="dialog-content">
        <h2>Edit Student</h2>
        <form @submit.prevent="updateStudent">
          <label>
            First Name:
            <input v-model="editedStudent.name.firstName" required />
          </label>
          <label>
            Last Name:
            <input v-model="editedStudent.name.lastName" required />
          </label>
          <label>
            Gender:
            <input v-model="editedStudent.gender" required />
          </label>
          <label>
            Date of Birth:
            <input type="date" v-model="editedStudent.dateOfBirth" required />
          </label>
          <label>
            Email:
            <input v-model="editedStudent.contact.email" required />
          </label>
          <label>
            Phone:
            <input v-model="editedStudent.contact.phoneNumber" required />
          </label>
          <label>
            Institute:
            <input v-model="editedStudent.academicDetails.instituteName" required />
          </label>
          <label>
            Program of Study:
            <input v-model="editedStudent.academicDetails.programOfStudy" required />
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
  name: 'StudentList',
  data() {
    return {
      students: [],
      error: null,
      showEditDialog: false,
      editedStudent: null
    };
  },
  mounted() {
    this.fetchStudents();
  },
  methods: {
    async fetchStudents() {
      try {
        const response = await fetch("api/StudentHomeBas/student/getall");
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        this.students = await response.json();
      } catch (error) {
        this.error = 'Error fetching data';
        console.error('There was a problem with the fetch operation:', error);
      }
    },
    openEditDialog(student) {
      this.editedStudent = { ...student };
      this.showEditDialog = true;
    },
    closeEditDialog() {
      this.showEditDialog = false;
      this.editedStudent = null;
    },
    async updateStudent() {
      try {
        const response = await fetch(`api/StudentHomeBas/student/update`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.editedStudent)
        });
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        await this.fetchStudents();
        this.closeEditDialog();
      } catch (error) {
        this.error = `Error updating student: ${error.message}`;
        console.error('There was a problem with the update operation:', error);
      }
    },

    async deleteStudent(userId) {
      if (confirm(`Are you sure you want to delete this student? ${userId}`)) {
        try {
          const response = await fetch(`api/StudentHomeBas/student/delete/${userId}`, {
            method: 'DELETE' // Send DELETE request
          });
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          await this.fetchStudents(); // Refresh the list after deletion
        } catch (error) {
          this.error = `Error deleting student: ${error.message}`; // Handle delete errors
          console.error('There was a problem with the delete operation:', error);
        }
      }
    }
  }
}
</script>

<style scoped>
.custom-background {
  background-color: #F9F9F9; /* Light grey background for the container */
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

.student-table {
  width: 100%;
  border-collapse: collapse;
}

.student-table th, .student-table td {
  padding: 12px;
  text-align: left;
  border: 1px solid #ddd;
}

.student-table th {
  background-color: #127670;
  color: white;
}

.student-table tr:nth-child(even) {
  background-color: #f2f2f2;
}

.action-buttons {
  display: flex;
  gap: 10px; /* Space between Edit and Delete buttons */
  align-items: center; /* Center align buttons vertically */
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
