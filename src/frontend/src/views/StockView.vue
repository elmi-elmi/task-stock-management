<template>
  <div >
    <v-card
        class="d-flex align-center flex-column mx-auto pa-4 mb-1" max-width="400px" min-height="300px" outlined elevation="18"
    >
      <NoSearchFallbackComp v-if="!stock.id" page="Stock"/>
      <DisplayResultComp :result="stock" v-else title="Stock"/>
      <InputAmountComp v-if="stock.id"/>

    </v-card>
    <SearchCardComp label="Search Stock" />

  </div>
</template>

<script>
import NoSearchFallbackComp from "@/components/NoSearchFallbackComp";
import DisplayResultComp from "@/components/DisplayResultComp";
import SearchCardComp from "@/components/SearchCardComp";
import InputAmountComp from "@/components/InputAmountComp";
export default{
  components:{SearchCardComp, NoSearchFallbackComp, DisplayResultComp, InputAmountComp},
  computed: {
    stock() {
      return this.$store.getters['product/getStock']
    }
  },
}
</script>
