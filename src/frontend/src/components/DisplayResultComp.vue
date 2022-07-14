<template>
  <div>
    <DeleteActionComp />

    <v-list-item class="mb-8">
      <v-list-item-content>
        <v-card min-width="260" class="d-flex align-center justify-space-between mb-2" flat tile>
          <div class="text-overline">{{ title }}</div>
          <div class="text-overline">{{ result.id }}</div>
        </v-card>

        <v-list-item-title>
          <span class="text-h5">{{ result.name }}</span>
        </v-list-item-title>

        <v-list-item-title>
          <span class="text-h5">{{ stockTween }}</span>
        </v-list-item-title>
      </v-list-item-content>
    </v-list-item>
  </div>
</template>

<script>
import DeleteActionComp from "@/components/request/DeleteActionComp";
export default {
  name: "DisplayResult",
  data() { return { alert: true } },
  components: { DeleteActionComp },

  props: {
    // title for card
    title: {
      type: String,
      require: true
    },
    // this is the object parant want to show
    result: {
      type: Object,
      require: true
    },
    stockTween: {
      type: String,
      required: true
    }
  },
  methods: {
    deleteResult() {
      console.log('*****')

    }
  }
}
</script>

<style scoped>
</style>
