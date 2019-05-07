import { IWoPickPack } from 'app/shared/model/wo-pick-pack.model';

export interface IWoAdditionalServices {
  id?: number;
  name?: string;
  woPickPacks?: IWoPickPack[];
}

export class WoAdditionalServices implements IWoAdditionalServices {
  constructor(public id?: number, public name?: string, public woPickPacks?: IWoPickPack[]) {}
}
