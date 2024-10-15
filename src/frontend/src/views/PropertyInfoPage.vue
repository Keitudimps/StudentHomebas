<template>
  <div id="property-info-page">
    <PropertyInfoNotLoggedHeader />
    <div class="content">
      <ImageGalleryComponent />
      <div class="details-section">
        <ApartmentDetailsComponent />
        <InquiryInfoComponent />
      </div>
    </div>
  </div>
</template>

<script>
import   PropertyInfoNotLoggedHeader from '@/components/PropertyInfoNotLoggedHeader.vue';
import ImageGalleryComponent from '@/components/ImageGalleryComponent.vue';
import ApartmentDetailsComponent from '@/components/ApartmentDetailsComponent.vue';
import InquiryInfoComponent from "@/components/InquiryInfoComponent.vue";



export default {
  name: "PropertyInfoPage",
  components: {

    PropertyInfoNotLoggedHeader,
    ImageGalleryComponent,
    ApartmentDetailsComponent,
    InquiryInfoComponent,

  },
};
</script>

<style scoped>
#property-info-page {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  color: #2c3e50;
}

.content {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 20px;
}

.details-section {
  display: flex;
  gap: 20px;
}
</style>
