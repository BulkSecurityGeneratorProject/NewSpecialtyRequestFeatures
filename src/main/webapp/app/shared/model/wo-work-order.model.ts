import { Moment } from 'moment';

export interface IWoWorkOrder {
  id?: number;
  customsBrokerName?: string;
  customsContactName?: string;
  customsCurrency?: string;
  customsPhoneNumber?: string;
  customsValue?: number;
  fromAirportCode?: string;
  fromLocationType?: number;
  jobContentDescr?: string;
  shipQuantity?: number;
  jobOriginalCost?: number;
  quotedAmount?: string;
  assignTo?: string;
  toCompany?: string;
  toLocationType?: number;
  dateCreated?: Moment;
  jobNumber?: string;
  jobCustomer?: string;
  serviceType?: number;
  jobSales?: string;
  jobDeadlineTime?: number;
  jobTimeZone?: number;
  shippingDate?: Moment;
  shippingTime?: number;
  insuranceType?: number;
  isStorage?: boolean;
  isPickPack?: boolean;
  shipTotalWeight?: string;
  shipmentCurrency?: string;
  shipmentMetric?: string;
  woRequestType?: number;
  woCustomsBrokerageId?: number;
  woStorageId?: number;
  woPickPackId?: number;
  woShippingInfoId?: number;
}

export class WoWorkOrder implements IWoWorkOrder {
  constructor(
    public id?: number,
    public customsBrokerName?: string,
    public customsContactName?: string,
    public customsCurrency?: string,
    public customsPhoneNumber?: string,
    public customsValue?: number,
    public fromAirportCode?: string,
    public fromLocationType?: number,
    public jobContentDescr?: string,
    public shipQuantity?: number,
    public jobOriginalCost?: number,
    public quotedAmount?: string,
    public assignTo?: string,
    public toCompany?: string,
    public toLocationType?: number,
    public dateCreated?: Moment,
    public jobNumber?: string,
    public jobCustomer?: string,
    public serviceType?: number,
    public jobSales?: string,
    public jobDeadlineTime?: number,
    public jobTimeZone?: number,
    public shippingDate?: Moment,
    public shippingTime?: number,
    public insuranceType?: number,
    public isStorage?: boolean,
    public isPickPack?: boolean,
    public shipTotalWeight?: string,
    public shipmentCurrency?: string,
    public shipmentMetric?: string,
    public woRequestType?: number,
    public woCustomsBrokerageId?: number,
    public woStorageId?: number,
    public woPickPackId?: number,
    public woShippingInfoId?: number
  ) {
    this.isStorage = this.isStorage || false;
    this.isPickPack = this.isPickPack || false;
  }
}
