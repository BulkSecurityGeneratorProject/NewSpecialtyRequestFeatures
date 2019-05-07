export interface IWoPackageType {
  id?: number;
  name?: string;
  woStorageId?: number;
}

export class WoPackageType implements IWoPackageType {
  constructor(public id?: number, public name?: string, public woStorageId?: number) {}
}
