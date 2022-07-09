import Vue from 'vue'
import VueRouter from 'vue-router'
import ProductView from '../views/ProductView.vue';
import StockView from '../views/StockView.vue'
import NotFoundView from "@/views/NotFoundView";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'product',
    component: ProductView
  },
  {
    path: '/stock',
    name: 'stock',
    component: StockView
  },
  {
    path:'/:catchAll(.*)',
    name:'notFound',
    component: NotFoundView
  },
  {
    path:'/404/:resource',
    name:'404Resource',
    component:NotFoundView,
    props:true
  }
]

const router = new VueRouter({
  routes,
  mode:'history'
})

export default router
