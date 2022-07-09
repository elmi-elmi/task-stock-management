
import mutations from "@/store/modules/product/mutations";
import actions from "@/store/modules/product/actions";
import getters from "@/store/modules/product/getters";


export default {
    namespaced: true,
    state: {
        product: {},
        stock: {}
    },
    mutations,
    actions,
    getters
}
