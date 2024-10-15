<template>
  <div class="property-list">
    <PropertyCard
        v-for="(property, index) in properties"
        :key="index"
        :image="property.image"
        :location="property.location"
        :type="property.type"
        :roomTypes="property.roomTypes"
        :description="property.description"
        :availableFrom="property.availableFrom"
        :price="property.price"
    />
  </div>
</template>

<script>
import PropertyCard from "./PropertyCard.vue";

export default {
  name: "PropertyList",
  components: {
    PropertyCard
  },
  data() {
    return {
      properties: [
        {
          image: require("@/assets/student_room.jpg"),
          location: "Mowbray",
          type: "House",
          roomTypes: "3 room types available",
          description: "Shared House in bright and sunny Zonnebloem",
          availableFrom: "January 2024",
          price: "R4,990"
        },
        {
          image: require("@/assets/student_room.jpg"),
          location: "Observatory",
          type: "Apartment",
          roomTypes: "2 room types available",
          description: "Stylish Apartments in Observatory",
          availableFrom: "January 2024",
          price: "R10,000"
        },
        {
          image: require("@/assets/student_room.jpg"),
          location: "District 6",
          type: "House",
          roomTypes: "3 room types available",
          description: "5 Room House in District 6 - Females only",
          availableFrom: "January 2024",
          price: "R4,990"
        },
        {
          image:require("@/assets/student_room.jpg"),
          location: "District 6",
          type: "House",
          roomTypes: "3 room types available",
          description: "5 Room House in District 6 - Females only",
          availableFrom: "January 2024",
          price: "R4,990"
        },{
          image: require("@/assets/student_room.jpg"),
          location: "Mowbray",
          type: "House",
          roomTypes: "3 room types available",
          description: "Shared House in bright and sunny Zonnebloem",
          availableFrom: "January 2024",
          price: "R4,990"
        },
        {
          image: require("@/assets/student_room.jpg"),
          location: "Observatory",
          type: "Apartment",
          roomTypes: "2 room types available",
          description: "Stylish Apartments in Observatory",
          availableFrom: "January 2024",
          price: "R10,000"
        },
        {
          image: require("@/assets/student_room.jpg"),
          location: "District 6",
          type: "House",
          roomTypes: "3 room types available",
          description: "5 Room House in District 6 - Females only",
          availableFrom: "January 2024",
          price: "R4,990"
        },
        {
          image:require("@/assets/student_room.jpg"),
          location: "District 6",
          type: "House",
          roomTypes: "3 room types available",
          description: "5 Room House in District 6 - Females only",
          availableFrom: "January 2024",
          price: "R4,990"
        }
      ]
    };
  }
};
</script>

<style scoped>
.property-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  padding: 20px;
  background-color: #f5f5f5;
  margin-bottom: 20px;
}
</style>
