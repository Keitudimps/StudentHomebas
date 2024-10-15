<template>
  <div class="price-inquiry">
    <div class="price">
      <p>From</p>
      <h2>R 26,500</h2>
      <p>/month</p>
    </div>
    <p class="availability">Available now</p>
    <button class="enquire-btn">Enquire</button>
  </div>
</template>

<script>
export default {
  name: "PriceInquiryComponent",
};
</script>

<style scoped>
.price-inquiry {
  padding: 20px;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.price p {
  margin: 0;
  color: #666;
}

.price h2 {
  margin: 10px 0;
  font-size: 28px;
  color: #333;
}

.availability {
  margin: 20px 0;
  color: #4caf50;
  font-weight: bold;
}

.enquire-btn {
  padding: 10px 20px;
  background-color: #ff4081;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.enquire-btn:hover {
  background-color: #e91e63;
}
</style>