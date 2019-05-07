export interface IWoStorage {
  id?: number;
  quantity?: number;
  spaceRequirement?: number;
  productInfo?: string;
  woPackageTypeId?: number;
  woWorkOrderId?: number;
}

export class WoStorage implements IWoStorage {
  constructor(
    public id?: number,
    public quantity?: number,
    public spaceRequirement?: number,
    public productInfo?: string,
    public woPackageTypeId?: number,
    public woWorkOrderId?: number
  ) {}
}
