import { IWoShippingInfo } from 'app/shared/model/wo-shipping-info.model';

export interface IWoShippingServices {
  id?: number;
  name?: string;
  woShippingInfos?: IWoShippingInfo[];
}

export class WoShippingServices implements IWoShippingServices {
  constructor(public id?: number, public name?: string, public woShippingInfos?: IWoShippingInfo[]) {}
}
