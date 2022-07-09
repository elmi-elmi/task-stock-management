<template>
  <div >
    <v-card
        class="d-flex align-center flex-column mx-auto pa-4 mb-1" max-width="400px" min-height="300px" outlined elevation="18"
    >
      <NoSearchFallbackComp v-if="!stock.id" page="Stock"/>
      <DisplayResultComp :result="stock" v-else title="Stock"/>
      <SellAndBuyComp v-if="stock.id"/>

    </v-card>
    <SearchByIdComp label="Search Stock By Id" />

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
}
</script>
