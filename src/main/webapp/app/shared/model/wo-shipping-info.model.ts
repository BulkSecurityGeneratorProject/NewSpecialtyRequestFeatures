import { IWoShippingServices } from 'app/shared/model/wo-shipping-services.model';

export interface IWoShippingInfo {
  id?: number;
  monthlyShipVolume?: number;
  isCustomsBrokerage?: boolean;
  woShippingServices?: IWoShippingServices[];
  woWorkOrderId?: number;
}

export class WoShippingInfo implements IWoShippingInfo {
  constructor(
    public id?: number,
    public monthlyShipVolume?: number,
    public isCustomsBrokerage?: boolean,
    public woShippingServices?: IWoShippingServices[],
    public woWorkOrderId?: number
  ) {
    this.isCustomsBrokerage = this.isCustomsBrokerage || false;
  }
}
