<template>
  <v-card
      class="mx-auto pa-4 d-flex align-center  "
      max-width="400px"
      min-height="120px"
      outlined
      elevation="18"
  >

    <v-text-field
        autofocus
        class="mb-2"
        v-model.number.trim="id"
        :label="label"
        @input="expandHandling"
        light
        flat
        clearable
        clear-icon="mdi-close-circle-outline"
        @keyup.enter="sendRequest"
        :rules="rules"

    ></v-text-field>

    <v-btn
        :disabled="!id"
        class="mx-2" fab dark small color="teal"
        @click="sendRequest"

    >
      <v-icon light>
        mdi-magnify
      </v-icon>
    </v-btn>

  </v-card>
</template>

<script>
export default {
  name: "SearchCardComp",
  data() {
    return {
      id: null,
      rules: [
        value => (typeof value === 'number' || value === '' || value == null) || 'ID is a number'
      ]
    }
  },
  emits: ['value'],
  props: {
    // label for input
    label: {
      type: String,
      default: 'Enter Id'
    },
    // v-model to update a value from parent
    value: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    // whether the result display expanding or not
    // this method update v-model(named id) from parent
    expandHandling() {
      // parant v-model update
      this.$emit('input', !!this.id)
    }
  },
  methods: {

    // dispatch action through the store
    sendRequest() {
      // which apis has been called -- product/:id or :id/stock
      const requestToStore = this.$route.name === 'product'
          ? 'product/fetchProductById'
          : 'product/fetchStockById'

      this.$store.dispatch(requestToStore, this.id)

          .then(() => this.id = null) // if request has been done successfully  the value in input clear
          .catch((e) => {
            // Todo -- Check Error status
            let name = '404Resource'
            if (e.code === "ERR_NETWORK") name = 'networkError'

            this.$router.push({name, params: {message: e.message, res: e.response.data}})
          })

    }
  },

}
</script>

<style scoped>

</style>
