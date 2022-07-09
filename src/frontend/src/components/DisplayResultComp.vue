<template>
  <v-list-item  three-line class="mb-12"
  >
    <v-list-item-content>
      <v-card
          class="d-flex align-center justify-space-between mb-2"
          flat
          tile
      >
        <div class="text-overline ">
          Product
        </div>
        <div class="text-overline ">
          {{ $store.getters['product/getProduct'].id }}
        </div>
      </v-card>

      <v-list-item-title class="text-h5 mb-1">
        {{ $store.getters['product/getProduct'].name }}
      </v-list-item-title>

      <v-list-item-subtitle>
        {{ $store.getters['product/getProduct'].stock }}
      </v-list-item-subtitle>

    </v-list-item-content>

  </v-list-item>
</template>

<script>
export default {
  name: "DisplayResult"
}
</script>

<style scoped>

</style>
