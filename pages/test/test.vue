<template>
	
	<view>
		<img :src="'data:image/png;base64,'+imgfile" class="avatar"/>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				value: 50,
				imgfile: ""
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
			onChange(value) {
				this.$toast('当前值：' + value);
			},
			base64ImgtoFile(dataurl, filename = 'file') {
			  let arr = dataurl.split(',')
			  let mime = arr[0].match(/:(.*?);/)[1]
			  let suffix = mime.split('/')[1]
			  let bstr = atob(arr[1])
			  let n = bstr.length
			  let u8arr = new Uint8Array(n)
			  while (n--) {
			    u8arr[n] = bstr.charCodeAt(n)
			  }
			  return new File([u8arr], `${filename}.${suffix}`, {
			    type: mime
			  })
			}
		}
	}
</script>

<style>

</style>
