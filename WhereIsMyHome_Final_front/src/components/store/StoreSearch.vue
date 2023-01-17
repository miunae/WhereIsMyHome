<template>
  <section class="page-section bg-primary" id="about">
		<div class="container px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-lg-12 text-center">
					<h2 class="text-white mt-0">동별 주변상권 조회</h2>
					<hr class="divider divider-light" />
					<form method="POST" id="search" action="">
						<input type="hidden" name="act" value="search" /> <input
							type="hidden" id="sidoValue" name="sido" value="" /> <input
							type="hidden" id="gugunValue" name="gugun" value="" /> <input
							type="hidden" id="dongValue" name="dong" value="" /> <input
							type="hidden" id="nameValue" name="name" value="" />
						<div class="row col-md-12 justify-content-center mb-2">
							<div class="form-group col-md-2">
								<select class="form-select bg-secondary text-light"
									id="condition" name="condition">
									<option value="">조건선택</option>
									<option value="byDong">동별</option>
									<!--option value="byApart">아파트별</option>  -->
								</select>
							</div>
							<div class="form-group col-md-2">
								<select ref="sido" class="form-select bg-secondary text-light" id="sido" 
                v-on:click="sidoChange($event)">
									<option  value="">시도선택</option>
								</select>
							</div>
							<div class="form-group col-md-2">
								<select class="form-select bg-secondary text-light" id="gugun"
                ref="gugun" v-on:click="gugunchange($event)">
									<option value="">구군선택</option>
								</select>
							</div>
							<div class="form-group col-md-2">
								<select class="form-select bg-secondary text-light" id="dong" ref="dong">
									<option value="">동선택</option>
								</select>
							</div>
						</div>
					</form>
					<div class="row col-md-12 justify-content-center mb-2">
						<div class="form-group col-md-2">
							<button type="button" id="list-btn" class="btn btn-dark"  @click="getstoreinfo">검색</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</template>

<script>
import {mapActions,mapState} from 'vuex'
const storeStore = "storeStore";
export default {
  
  name:"StoreSearch",
  data() {
    return {
      payload:{
      sido:"",
      gugun:"",
      dong:"",
      },

    }
  },
  computed:{
    ...mapState(storeStore,["stores"])
  },
  created(){
    console.log(this.stores);
    this.sendRequest("sido", "*00000000");
  },
  methods: {
    ...mapActions(storeStore,["getStoreInfo"]),
    getstoreinfo(){
      console.log("call storeinfo");
      console.log(this.$refs.sido.options[this.$refs.sido.selectedIndex].text);
      this.payload.sido=this.$refs.sido.options[this.$refs.sido.selectedIndex].text;
      this.payload.gugun=this.$refs.gugun.options[this.$refs.gugun.selectedIndex].text;
      this.payload.dong=this.$refs.dong.options[this.$refs.dong.selectedIndex].text;
      console.log(this.payload.gugun,this.payload.dong);

      this.getStoreInfo(this.payload).then();
      this.$router.push({name:"storelist"}).catch(()=>{console.log("재검색")});
    },
    sidoChange(event){
      console.log("hi");
      console.log(event.target.value);
      var sido = document.querySelector("#sido");
    if (sido.options[sido.selectedIndex].value) {
      let regcode = sido.options[sido.selectedIndex].value.substr(0, 2) + "*00000";
      this.sendRequest("gugun", regcode);
    } else {
      this.initOption("gugun");
      this.initOption("dong");
    }
    },
    gugunchange(event){
      console.log(event.target.value);
      var gugun = document.querySelector("#gugun");
    if (gugun.options[gugun.selectedIndex].value) {
      let regcode = gugun.options[gugun.selectedIndex].value.substr(0, 5) + "*";
      this.sendRequest("dong", regcode);
    } else {
      this.initOption("dong");
    }
    },
    sendRequest(selid, regcode) {
    const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
    let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
    fetch(`${url}?${params}`)
      .then((response) => response.json())
      .then((data) => this.addOption(selid, data));
  },
  initOption(selid) {
    let options = document.querySelector(`#${selid}`);
    options.length = 0;
  },
  addOption(selid, data) {
    let opt = ``;
    this.initOption(selid);
    switch (selid) {
      case "sido":
        opt += `<option value="">시도선택</option>`;
        data.regcodes.forEach(function (regcode) {
          opt += `
      <option value="${regcode.code}">${regcode.name}</option>
      `;
        });
        break;
      case "gugun":
        opt += `<option value="">구군선택</option>`;
        for (let i = 0; i < data.regcodes.length; i++) {
          if (i != data.regcodes.length - 1) {
            if (
              data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
              data.regcodes[i].name.split(" ").length !=
                data.regcodes[i + 1].name.split(" ").length
            ) {
              data.regcodes.splice(i, 1);
              i--;
            }
          }
        }
        var name = "";
        data.regcodes.forEach(function (regcode) {
          if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
          else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
          opt += `
      <option value="${regcode.code}">${name}</option>
      `;
        });
        break;
      case "dong":
        opt += `<option value="">동선택</option>`;
        var idx = 2;
        data.regcodes.forEach(function (regcode) {
          if (regcode.name.split(" ").length != 3) idx = 3;
          opt += `
      <option value="${regcode.code}">${regcode.name.split(" ")[idx]}</option>
      `;
        });
    }
    document.querySelector(`#${selid}`).innerHTML = opt;
  },

  },
}
</script>

<style>

</style>
