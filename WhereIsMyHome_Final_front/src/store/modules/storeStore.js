// import http from "@/api/lib/http";
// import router from "@/router/index";

import http from '@/api/lib/http';


const storeStore ={
  namespaced: true,

  state: {
    sido: "",
    gugun: "",
    dong: "",
    stores: [],
    positions:[],
  },
  getters: {
    
  },

  mutations: {
    SET_STORE_LIST(state,data){
      // this.sido=
      state.stores = data;
      state.positions = [];
      for (let index in state.stores) {
        // let tmp = [];
        // console.log("store한개", state.stores[index]);
        state.positions.push([parseFloat(state.stores[index].storeLat),parseFloat(state.stores[index].storeLng)]);
      }
      console.log("포지션즈:",state.positions);
    }
  },
  actions: {
    // getSido({ commit }) {
    //   http.get
    // }
    getStoreInfo({commit},payload){
      console.log("payload : ",payload);
      http.get(`/store/list/${payload.sido}/${payload.gugun}/${payload.dong}`)
      .then(({data})=>{
        console.log(data);
        commit("SET_STORE_LIST",data);
      })
    }
  },
  modules: {
  },
};
export default storeStore;