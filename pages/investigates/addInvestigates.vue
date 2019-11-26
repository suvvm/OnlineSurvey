<template>
	<view>
		<van-button icon="share" size="large" type="primary" @click="submit()">提交审核</van-button>
		<van-button icon="plus" size="large" type="info" @click="addTag()">新增问题</van-button>
		<van-popup v-model="showAddQst" position="bottom">
			<van-radio-group v-model="radio">
				<van-cell-group>
					<van-cell title="选择题" clickable @click="radio = '1'">
						<van-radio slot="right-icon" name="1" />
					</van-cell>
					<van-cell title="简答题" clickable @click="radio = '2'">
						<van-radio slot="right-icon" name="2" />
					</van-cell>
				</van-cell-group>
			</van-radio-group>
		</van-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				questionList: [],
				userInfo: {},
				showAddQst: false,
				radio: '0'
			}
		},
		onLoad() {
			if(this.$cookies.get("userInfo") != null){	// 获取当前用户信息
				this.userInfo = this.$cookies.get("userInfo");
			}
		},
		methods: {
			addTag() {
				this.showAddQst = true;
			}
		}
	}
</script>

<style>

</style>
