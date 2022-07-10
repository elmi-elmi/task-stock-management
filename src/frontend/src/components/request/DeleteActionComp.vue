<template>
  <v-card-actions
      class="pa-0"
  >
    <v-spacer></v-spacer>
    <v-icon
        @click="deleteShowedResult"

    >
      mdi-close
    </v-icon>
  </v-card-actions>
</template>

<script>
export default {
  name: "DeleteActionComp",
  methods:{
    deleteShowedResult(){
      //
      const requestToStore = this.$route.name === 'product'
          ? 'product/DELETE_PRODUCT'
          : 'product/DELETE_STOCK'

      this.$store.commit(requestToStore,)
    }
  }
}
</script>

<style scoped>

</style>
