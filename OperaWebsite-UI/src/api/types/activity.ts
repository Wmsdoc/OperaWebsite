export interface Activity {
	/**
	 * 活动编号
	 */
	activityId?: string
	/**
	 * 活动名称
	 */
	activityName?: string
	/**
	 * 活动简介
	 */
	activityInfo?: string
	/**
	 * 活动地址
	 */
	activityAddress?: string
	/**
	 * 活动发起人
	 */
	playgoerId?: string
	/**
	 * 活动人数
	 */
	activityNum?: number
	/**
	 * 活动开始时间
	 */
	startTime?: Date
	/**
	 * 活动结束时间
	 */
	endTime?: Date
	/**
	 * 是否删除
	 */
	isDelete?: number
	/**
	 * 创建时间
	 */
	createdAt?: Date
	/**
	 * 更新时间
	 */
	updatedAt?: Date
	/**
	 * 创建人
	 */
	createdBy?: string
	/**
	 * 修改人
	 */
	updatedBy?: string
}
export interface ActivityVO {
	/**
	 * 发布者编号
	 */
	playgoerId?: string
	/**
	 * 发布者头像
	 */
	playgoerAvatar?: string
	/**
	 * 发布者姓名
	 */
	playgoerName?: string
	/**
	 * 发布者签名
	 */
	playgoerInfo?: string
	/**
	 * 活动名称
	 */
	activityName?: string
	/**
	 * 活动简介
	 */
	activityInfo?: string
	/**
	 * 活动地址
	 */
	activityAddress?: string
	/**
	 * 活动剩余名额
	 */
	activityQuota?: number
	/**
	 * 活动人数
	 */
	activityNum?: number
	/**
	 * 活动开始时间
	 */
	startTime?: Date
	/**
	 * 活动结束时间
	 */
	endTime?: Date
	/**
	 * 创建时间
	 */
	createdAt?: Date
	/**
	 * 更新时间
	 */
	updatedAt?: Date
}
