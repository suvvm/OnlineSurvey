<template>
	
	<view>
		<view v-for="item of 5">
			{{testList[nowbg + item - 1]}}
		</view>
		<van-pagination
		  v-model="currentPage" 
		  :total-items="testList.length" 
		  :items-per-page="5"
		  @change="onPaginationChanged()"
		/>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				value: 50,
				imgfile: "",
				nowbg: 0,
				currentPage: 1,
				testList: [{"name":"1"},{"name":"2"},{"name":"3"},{"name":"4"},
				{"name":"5"},{"name":"6"},{"name":"7"},{"name":"8"},{"name":"9"},
				{"name":"10"},{"name":"11"},{"name":"12"},{"name":"13"},{"name":"14"},
				{"name":"15"},{"name":"16"},{"name":"17"},{"name":"18"},{"name":"19"}]
			}
		},
		onLoad() {
			if(this.$cookies.get("userInfo") != null){
				this.userInfo = this.$cookies.get("userInfo");
			}
			var rp = require('request-promise');
			var options = {
			    method: 'POST',
			    uri: 'http://localhost:8080/getUserById',
			    form: {
			        id: this.userInfo.id,
			    }
			};
			rp(options).then(res => {
				this.$toast.clear();
				if(res == "error") {
					this.$toast.fail('获取用户信息识别');
				} else {
					var userInfo = JSON.parse(res);
					console.log(userInfo);
					// var base64Img = 'data:image/png;base64,' + userInfo.imgbase64;
					// var imgFile = this.base64ImgtoFile(base64Img);
					// console.log(base64Img);
					// console.log(imgFile);
					// this.imgfile = imgFile;
					this.imgfile = userInfo.imgbase64;
					this.$toast.success('获取用户信息识别成功');
				}
				// console.log(res)
			}).catch(err => {
				this.$toast.clear();
				this.$toast.fail('获取用户信息识别');
				console.log(err)
			});
		},
		methods: {
			onPaginationChanged(){
				this.nowbg = this.currentPage * 5 - 5
			}
		},
		
	}
</script>

<style>

</style>
