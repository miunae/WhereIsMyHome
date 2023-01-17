import { sidoList, gugunList, dongList, houseList, aptList } from "@/api/house.js";
import http from "@/api/lib/http";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    apts: [],
    house: null,
    deals: [],
  },
  getters: {},
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    CLEAR_DEAL_LIST(state){
      state.deals = [];
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_APT_LIST(state, apts) {
      state.apts = apts;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_DEAL_LIST(state, deals) {
      state.deals = deals;
    }
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = { sido: sidoCode };
      gugunList(
        params,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = { gugun: gugunCode };
      dongList(
        params,
        ({ data }) => {
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList: ({ commit }, dongCode) => {
      // console.log(dongCode);
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      const params = {
        LAWD_CD: dongCode.substr(0, 5),
        DEAL_YMD: "202207",
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      houseList(
        params,
        ({ data }) => {
          console.log(data);
          commit("SET_HOUSE_LIST", data.response.body.items.item);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAptList: ({ commit }, dongCode) => {
      // console.log(dongCode);
      const params = {
        dong: dongCode,
      };
      aptList(
        params,
        ({ data }) => {
          // console.log(data);
          commit("SET_APT_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, aptCode) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", aptCode);
    },
    getDealList({ commit }, aptCode){
      console.log(aptCode);
      http.get(`/map/deallist/${aptCode}`)
        .then(({ data }) => {
          console.log(data);
          commit("SET_DEAL_LIST", data);
        });
    }
    // getDealList: ({ commit }, aptCode) => {
    //   const aptCode = aptCode;
    //   dealList(
    //     ({ aptCode }) => {
    //       commit("SET_DEAL_LIST", aptCode);
    //     },
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    // }
  },
  modules: {
  },
};

export default houseStore;
