import http from "@/api/lib/http";
import router from "@/router/index";
const qnaStore = {
    namespaced: true,

    state: {
        qnas: [],
        qna: null,
        answers: [],
        // answer:{},
    },
    getters: {
    },
    mutations: {
        SET_QNA_LIST(state, qnas) {
            state.qnas = qnas;
        },
        SET_ANSWER_LIST(state, answers) {
            state.answers = answers;
        },
        SET_DETAIL_QNA(state, qna) {
            console.log("state.qna:", state.qna);
            state.qna = qna;
            router.push({ name: "qnaview" });
            // console.log(this);
        },
        DELETE_QNA(state, msg) {
            alert(msg);
        },
        UPDATE_QNA(state, msg) {
            console.log(msg);
        },
        WRTIE_QNA(state, msg) {
            console.log(msg);
        },
        WRITE_ANSWER(state, msg) {
            console.log(msg);
        },
      DELETE_ANSWER(state, { ansNo, msg }) {
            let index = state.answers.findIndex((x) => x.ansNo === ansNo);
          state.answers.splice(index, 1);
          console.log(msg);
            alert(msg);
        },

    },
    actions: {
        getQna({ commit }) {
            http.get(`/qna`).then(({ data }) => {
                console.log("data: ", data);
                commit("SET_QNA_LIST", data);
            })
                .catch((error) => {
                    console.log(error);
                })
        },
        getAnswers({ commit }, qnano) {
            http.get(`/qna/answer/${qnano}`).then(({ data }) => {
                commit("SET_ANSWER_LIST", data);
            })
                .catch((error) => {
                    console.log(error);
                })
        },
        detailQna({ commit }, qna) {
            console.log("qna:", qna);
            console.log("this.$route:", this.$router);
            http.get(`/qna/${qna.qnano}`).then(({ data }) => {
                console.log("data: ", data);
                commit("SET_DETAIL_QNA", qna);
            })
        },
        deleteQna({ commit }, qnano) {
            http.delete(`/qna/${qnano}`)
                .then(({ data }) => {
                    let msg = "?????? ????????? ????????? ??????????????????.";
                    if (data === "success") {
                        msg = "????????? ?????????????????????.";
                        commit("DELETE_QNA", msg);
                    }
                })
        },
        modifyQna({ commit }, qna) {
            http.put(`/qna/`, qna).then(({ data }) => {
                let msg = "?????? ????????? ????????? ??????????????????.";
                if (data === "success") {
                    msg = "????????? ??????????????????.";
                    commit("UPDATE_QNA", msg);
                }
            })
        },
        createQna({ commit }, qna) {
            console.log("qna:", qna);
            http.post(`/qna`, qna).then(({ data }) => {
                let msg = "????????? ????????? ??????????????????.";
                if (data === "success") {
                    msg = "????????? ??????????????????.";
                    commit("WRTIE_QNA", msg);
                }
            })
        },
        createAnswer({ commit }, answer) {
            console.log("answer:", answer);
            http.post(`/qna/answer`, answer).then(({ data }) => {
                let msg = "????????? ????????? ??????????????????.";
                if (data === "success") {
                    msg = "????????? ??????????????????.";
                    commit("WRITE_ANSWER", msg);
                }
            })
        },
        deleteAnswer({ commit },  ansNo ) {
            http.delete(`/qna/answer/${ansNo}`)
                .then(({ data }) => {
                     let msg = "?????? ????????? ????????? ??????????????????.";
                    if (data === "success") {
                        msg = "????????? ?????????????????????.";
                      commit("DELETE_ANSWER", { ansNo, msg });
                      }
                })
        },
    },
    modules: {
    },
};
export default qnaStore;