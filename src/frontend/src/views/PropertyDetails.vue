<template>
  <div>
    <h2>{{ property.location }}</h2>
    <img :src="property.image" alt="Property Image" />
    <p>Type: {{ property.type }}</p>
    <p>Rooms: {{ property.rooms }}</p>
    <p>Price: {{ property.price }}/month</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      property: {
        location: 'Observatory',
        type: 'Apartment',
        rooms: 2,
        price: 'R10,000',
        image: 'apartment1.jpg',
      },
    };
  },
};
</script>
