<template>
  <div

  >

    <v-expand-transition
        class="mb-2"
    >
    <v-card
        v-show="expand || product.id"
        class=" mx-auto  mb-1 "
        width="400px"
        height="300px"
        outlined elevation="18"
    >
      <NoSearchFallbackComp v-if="!product.name"/>
      <DisplayResultComp v-else :result="product" title="Product"/>
      <SellAndBuyComp v-if="product.name"/>

    </v-card>
    </v-expand-transition>


    <SearchByIdComp v-model="expand"  />


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
  data(){return{expand: false,}},

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


