import Vue from 'vue'
import VueRouter from 'vue-router'
import ProductView from '../views/ProductView.vue';
import StockView from '../views/StockView.vue'
import NotFoundView from "@/views/NotFoundView";
import NetworkErrorView from "@/views/NetworkErrorView";
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
  },
  {
    path:'/500',
    name:'networkError',
    component: NetworkErrorView,
    props:true
  }
]

const router = new VueRouter({
  routes,
})

export default router
