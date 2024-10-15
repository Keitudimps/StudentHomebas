<template>
  <div class="additional-info">
    <h4>Rooms in apartment for rent</h4>
    <p><strong>From</strong><br/><b>R 10,000</b>/ month</p>
    <p><strong>Available now</strong></p>
    <div class="action-section">
      <button class="inquire-button">Inquire</button>
      <font-awesome-icon icon="heart" class="heart-icon" />
    </div>
    <hr class="divide">
    <p><strong>Concierge</strong></p>

    <p><font-awesome-icon icon="calendar" class="icon"/> Joined DigsConnect in 2021</p>

    <p><font-awesome-icon icon="clock" class="icon"/> Replies in 21.9 hours</p>

    <p>      <font-awesome-icon icon="info" class="icon"/>   95% reply rate</p>
    <p><strong>Trusted landlord</strong></p>
  </div>
</template>

<script>
export default {
  name: 'InquiryInfoComponent',
};
</script>

<style scoped>
.additional-info {
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 20px;
  padding: 20px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}
.icon {
  font-size: 20px;
  color: rgba(19, 15, 15, 0.75);

}
.additional-info h4 {
  margin-top: 0;
}
.action-section {
  display: flex;
  align-items: center;
  gap: 10px;
  margin: 10px 0;
}

.inquire-button {
  background-color: #00796b;
  color: white;
  border: none;
  border-radius: 10px;
  padding: 10px 20px;
  font-size: 24px;
  cursor: pointer;
  width:300px;

}

.inquire-button:hover {
  background-color: #004d40;
}

.heart-icon {
  font-size: 24px;
  width:45px;
  height: 45px;
  color: #9f9d9d;

}
.heart-icon:hover {
  color: red;
}

</style>
