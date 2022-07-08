<template>
  <v-app id="inspire">
    <v-app-bar
        app
        color="white"
        flat
    >


      <v-tabs
          centered
          class="ml-n9"
          color="grey darken-1"
      >
        <v-tab
            v-for="link in links"
            :key="link"
        >
          {{ link }}
        </v-tab>
      </v-tabs>

    </v-app-bar>

    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col
              cols="12"
              sm="2"
          >

          </v-col>

          <v-col
              cols="12"
              sm="8"
          >

              <router-view/>

          </v-col>


        </v-row>

      </v-container>
    </v-main>
  </v-app>

</template>

<script>

export default {
  name: 'App',
  data: () => ({
    //
    links: [
      'Product',
      'Stock',

    ],
    page:2
  }),
};
</script>
