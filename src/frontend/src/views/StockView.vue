<template>
  <div >
    <v-expand-transition
    >
    <v-card
        v-show="expand || stock.id"
        class=" mx-auto  mb-1 "
        width="400px"
        height="300px"
        outlined elevation="18"
    >
      <NoSearchFallbackComp v-if="!stock.id" page="Stock"/>
      <DisplayResultComp :result="stock" v-else title="Stock"/>
      <SellAndBuyComp v-if="stock.id"/>

    </v-card>
    </v-expand-transition>
    <SearchByIdComp v-model="expand" label="Search Stock By Id" />

  </div>
</template>

<script>

import NoSearchFallbackComp from "@/components/NoSearchFallbackComp";
import DisplayResultComp from "@/components/DisplayResultComp";
import SearchByIdComp from "@/components/request/SearchByIdComp";
import SellAndBuyComp from "@/components/request/SellAndBuyComp";


export default{
  components:{SearchByIdComp, NoSearchFallbackComp, DisplayResultComp, SellAndBuyComp},
  computed: {
    stock() {
      return this.$store.getters['product/getStock']
    }
  },
  data(){return{expand: false,}},

}
</script>
