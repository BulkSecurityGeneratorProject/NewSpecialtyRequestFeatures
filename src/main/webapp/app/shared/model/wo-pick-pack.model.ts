import { IWoAdditionalServices } from 'app/shared/model/wo-additional-services.model';

export interface IWoPickPack {
  id?: number;
  sku?: number;
  avgOrders?: number;
  shipmentWeight?: number;
  shipmentSize?: number;
  monthlyVolume?: number;
  isPromotionalInserts?: boolean;
  description?: string;
  woAdditionalServices?: IWoAdditionalServices[];
  woWorkOrderId?: number;
}

export class WoPickPack implements IWoPickPack {
  constructor(
    public id?: number,
    public sku?: number,
    public avgOrders?: number,
    public shipmentWeight?: number,
    public shipmentSize?: number,
    public monthlyVolume?: number,
    public isPromotionalInserts?: boolean,
    public description?: string,
    public woAdditionalServices?: IWoAdditionalServices[],
    public woWorkOrderId?: number
  ) {
    this.isPromotionalInserts = this.isPromotionalInserts || false;
  }
}
