<template>
  <div>
    <h2>Contact Us</h2>
    <form>
      <label for="name">Name:</label>
      <input type="text" id="name" name="name" required />

      <label for="email">Email:</label>
      <input type="email" id="email" name="email" required />

      <label for="message">Message:</label>
      <textarea id="message" name="message" required></textarea>

      <button type="submit">Send</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'ContactPage',
};
</script>

<style scoped>
form {
  max-width: 400px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
}
input, textarea {
  margin-bottom: 15px;
  padding: 10px;
}
button {
  padding: 10px;
}
</style>
