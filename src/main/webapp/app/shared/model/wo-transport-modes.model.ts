import { IWoCustomsBrokerage } from 'app/shared/model/wo-customs-brokerage.model';

export interface IWoTransportModes {
  id?: number;
  name?: string;
  woCustomsBrokerages?: IWoCustomsBrokerage[];
}

export class WoTransportModes implements IWoTransportModes {
  constructor(public id?: number, public name?: string, public woCustomsBrokerages?: IWoCustomsBrokerage[]) {}
}
