<template>
  <div class="container-fluid chat-app">
    <div class="row">
      <!-- Chat Sidebar for Listing Landlords -->
      <div v-if="landlords.length > 0" class="col-md-4 col-lg-3 p-0 chat-sidebar">
        <div class="list-group list-group-flush">
          <!-- List of landlords -->
          <a
              v-for="(landlord, index) in landlords"
              :key="landlord.id"
              href="#"
              class="list-group-item list-group-item-action"
              :class="{ active: selectedLandlordIndex === index }"
              @click="selectLandlord(index)"
          >
            <!-- Icon with the first letter of the landlord's name -->
            <div class="landlord-icon me-2">
              {{ landlord.name.firstName.charAt(0).toUpperCase() }}
            </div>
            {{ landlord.name.firstName }}
          </a>
        </div>
      </div>

      <!-- Chat Window -->
      <div class="col-md-8 col-lg-9 p-0 chat-window" v-if="selectedLandlord">
        <div class="card h-100">
          <div class="card-header bg-light d-flex align-items-center">
            <!-- Landlord's Name and Icon -->
            <div class="landlord-icon me-2">
              {{ selectedLandlord.name.firstName.charAt(0).toUpperCase() || '' }}
            </div>
            <i class="bi bi-person-circle me-2" style="font-size: 1.5rem;"></i>
            <h5 class="mb-0">{{ selectedLandlord.name.firstName || "Select a landlord" }}</h5>
          </div>
          <div class="card-body chat-body">
            <!-- Display chat messages -->
            <div class="messages-container">
              <div
                  v-for="(message, index) in selectedLandlord?.messages || []"
                  :key="index"
                  class="d-flex mb-2"
                  :class="message.senderId === student.userId ? 'justify-content-end' : 'justify-content-start'"
              >
                <!-- Only show the icon for the landlord -->
                <div v-if="message.senderId !== student.userId" class="landlord-icon">
                  {{ selectedLandlord.name.firstName.charAt(0).toUpperCase() }}
                </div>
                <div
                    :class="message.senderId === student.userId ? 'chat-bubble chat-bubble-right' : 'chat-bubble chat-bubble-left'"
                >
                  {{ message.text }}
                  <div class="message-time">{{ formatTimestamp(message.timestamp) }}</div>
                </div>
              </div>
            </div>
          </div>
          <div class="card-footer bg-light">
            <div class="input-group">
              <input
                  v-model="newMessage"
                  type="text"
                  class="form-control"
                  placeholder="Type your message..."
                  :disabled="!selectedLandlord"
              />
              <button class="btn btn-primary" type="button" @click="sendMessage" :disabled="!selectedLandlord">
                Send
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- If no landlord is selected or no messages exist, show a prompt -->
      <div v-else class="col-md-12 p-0 d-flex align-items-center justify-content-center">
        <div class="text-muted">No messages yet!</div>
      </div>
    </div>
  </div>
</template>

<script>
import StudentService from '@/service/StudentService';
import MessageService from '@/service/MessageService';

export default {
  name: 'StudentChat',
  data() {
    return {
      student: null, // Stores student information
      landlords: [], // List of landlords who have messaged the student
      selectedLandlordIndex: 0, // Default to the first landlord
      newMessage: '', // Model for the new message input
    };
  },
  computed: {
    selectedLandlord() {
      return this.landlords[this.selectedLandlordIndex];
    },
  },
  methods: {
    async fetchStudentAndConversations() {
      try {
        // Fetch the student's data using the StudentService
        const studentId = 1; // Use the correct student ID
        this.student = await StudentService.readStudent(studentId);

        // Fetch messages for the current student
        const messages = await MessageService.fetchMessagesForUser(studentId);

        if (messages.length === 0) {
          // If no messages are found, clear landlords and do not display the sidebar
          this.landlords = [];
          return;
        }

        // Process messages to organize by landlord
        const landlordMap = {};
        messages.forEach((message) => {
          // Determine the other user involved in the conversation
          const otherUser =
              message.sender.userId === studentId ? message.receiver : message.sender;

          if (!landlordMap[otherUser.userId]) {
            landlordMap[otherUser.userId] = {
              id: otherUser.userId,
              name: otherUser.name,
              messages: [],
            };
          }

          // Add the message to the corresponding landlord
          landlordMap[otherUser.userId].messages.push({
            senderId: message.sender.userId,
            text: message.content,
          });
        });

        // Convert the map to an array
        this.landlords = Object.values(landlordMap);

        // Default to the first landlord if available
        if (this.landlords.length > 0) {
          this.selectedLandlordIndex = 0;
        }
      } catch (error) {
        console.error('Error fetching student or conversations:', error);
      }
    },
    selectLandlord(index) {
      this.selectedLandlordIndex = index;
    },
    async sendMessage() {
      if (this.newMessage.trim() !== '' && this.selectedLandlord) {
        const messageDTO = {
          content: this.newMessage,
          senderId: this.student.userId, // Use the fetched student's userId
          receiverId: this.selectedLandlord.id, // Send to the selected landlord's userId
        };

        try {
          // Send the message to the database
          await MessageService.sendMessage(messageDTO);

          // Add the message to the local state for immediate feedback
          this.selectedLandlord.messages.push({
            senderId: this.student.userId,
            text: this.newMessage,
          });

          // Clear the input field
          this.newMessage = '';
        } catch (error) {
          console.error('Error sending message:', error);
        }
      }
    },
    formatTimestamp(timestamp) {
      console.log("Timestamp:", timestamp);
      const messageDate = new Date(timestamp);
      return messageDate.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', hour12: false });
    }
  },
  async mounted() {
    // Fetch the student and conversations when the component is mounted
    await this.fetchStudentAndConversations();
  },
};
</script>

<style scoped>
.chat-app {
  height: 100vh; /* Full height for the chat app */
}

.chat-sidebar {
  background-color: #d8d8d8; /* Same as the form background */
  border-right: 1px solid #ddd;
  overflow-y: auto; /* Allow scrolling if the list of landlords is long */
}

.landlord-icon {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 30px;
  height: 30px;
  background-color: #127670;
  color: white;
  border-radius: 50%;
  font-weight: bold;
}

.chat-window {
  display: flex;
  flex-direction: column;
  height: 100vh; /* Full height for the chat window */
}

.card-body {
  display: flex;
  flex-direction: column;
  height: calc(100vh - 150px); /* Adjust based on header and footer height */
  padding: 0;
}

.messages-container {
  overflow-y: auto; /* Enable scrolling if the content grows */
  flex-grow: 1;
  padding: 20px;
  max-height: 100%; /* Ensure container fills available space */
}

.chat-bubble {
  position: relative; /* Allows absolute positioning of the timestamp */
  max-width: 75%;
  padding: 10px 15px;
  padding-bottom: 20px; /* Increase bottom padding to make space for the timestamp */
  border-radius: 20px;
  margin-bottom: 10px; /* Increase bottom margin to ensure spacing between messages */
}

.chat-bubble-left {
  background-color: #f1f1f1; /* Bubble color for received messages */
}

.chat-bubble-right {
  background-color: #127670; /* Bubble color for sent messages */
  color: white;
}

.message-time {
  position: absolute; /* Positioned relative to .chat-bubble */
  bottom: 5px; /* Distance from the bottom of the bubble */
  right: 10px; /* Distance from the right edge of the bubble */
  font-size: 0.6em;
  color: gray;
}

</style>
