<template>
  <div>
    <v-card class="d-flex align-center flex-column mx-auto pa-4 mb-1" max-width="400px" min-height="300px" outlined elevation="18"
    >
      <NoSearchFallbackComp v-if="!product.name"/>
      <DisplayResultComp v-else :result="product" title="Product"/>
      <SellAndBuyComp v-if="product.name"/>

    </v-card>

    <SearchByIdComp />


  </div>

</template>

<script>

import SearchByIdComp from "@/components/request/SearchByIdComp";
import SellAndBuyComp from "@/components/request/SellAndBuyComp";
import NoSearchFallbackComp from "@/components/NoSearchFallbackComp";
import DisplayResultComp from "@/components/DisplayResultComp";

export default {
  name: 'ProductView',
  components: {SearchByIdComp, SellAndBuyComp, NoSearchFallbackComp,DisplayResultComp},

  computed: {
    product() {
      return this.$store.getters['product/getProduct']
    }
  },
  stock() {
    console.log('*^&%&^%&%^$^%$')
    console.log(this.$store.getters['product/getStock'])
    return this.$store.getters['product/getStock']
  }

}
</script>


<style lang="scss">


</style>
