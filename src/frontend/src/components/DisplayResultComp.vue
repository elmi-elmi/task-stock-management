<template>
  <div
  class="pa-4"
  >
  <v-list-item
      three-line class="mb-12  "

  >
    <v-list-item-content>
      <v-card
          min-width="260"
          class="d-flex align-center justify-space-between mb-2 "
          flat
          tile
      >
        <div class="text-overline ">
          {{ title }}
        </div>
        <div class="text-overline ">
          {{ result.id }}
        </div>
      </v-card>

      <v-list-item-title
          v-for="(item, key) in result"
          :key="key"
          class="text-h5 mb-1">
        <span v-if="key!=='id' && key !=='reservations'">{{ item }}</span>
      </v-list-item-title>


    </v-list-item-content>

  </v-list-item>
  </div>
</template>

<script>
export default {
  name: "DisplayResult",
  computed: {
    product() {
      return this.$store.getters['product/getProduct']
    }
  },
  props: {
    title: {
      type: String,
      require: true
    },
    result: {
      type: Object,
      require: true
    }
  }
}
</script>

<style scoped>

</style>
